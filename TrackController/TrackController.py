from tkinter import ttk
import tkinter as tk
from tkinter.scrolledtext import ScrolledText
from tkinter import filedialog
import xml.etree.ElementTree
import os
import boolean
from pathlib import Path



# Creates main window
class MainWindow(tk.Frame):
    tabcounter = 1
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
        root.geometry("605x480")
        self.tab_frame = ttk.Notebook(root)
        self.tab_frame.grid(column = 1, row = 1)
        
        menubar = tk.Menu(root)
        nb = ttk.Notebook(root)
		
        # create a pulldown menu, and add it to the menu bar
        filemenu = tk.Menu(menubar, tearoff=0)
        filemenu.add_command(label="Create New Track Controller", command=self.addTab)
        filemenu.add_command(label="Exit", command=root.quit)
        menubar.add_cascade(label="File", menu=filemenu)

        helpmenu = tk.Menu(menubar, tearoff=0)
        helpmenu.add_command(label="About")
        menubar.add_cascade(label="Help", menu=helpmenu)

        # display the menu
        root.config(menu=menubar)
        self.addTab()

    def addTab(self):
	#Create new tab and ask for PLC file
        tab = PanelFrame(self.tab_frame)
        try:
            self.tab_frame.add(tab, text = self.tabcounter)
            self.tabcounter += 1
        except:
            print("No file selected")

#Creates panel frame after PLC file is selected			
class PanelFrame(ttk.Frame):
    Test = True
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
        self.filename = filedialog.askopenfilename(initialdir = str(Path(os.path.abspath(__file__)).parents[1])+"\\PLC\\",title = "Select file",filetypes = (("XML","*.xml"),("All Files","*.*")))
        if self.filename != '':
            print ("Logic file has been loaded")
            self.testPanel()
        else:
            self.destroy()
			
	#Creates input, output, and logic treeviews
    def testPanel(self):
        self.inputTree = ttk.Treeview(self, columns=('Type', 'Value'))
        inputvsb = ttk.Scrollbar(self, orient="vertical", command=self.inputTree.yview)
        self.inputTree.configure(yscrollcommand=inputvsb.set)
        self.inputTree.heading('#0', text='Bit')
        self.inputTree.heading('#1', text='Type')
        self.inputTree.heading('#2', text='Value')
        self.inputTree.column('#1', width = 100, stretch=tk.NO)
        self.inputTree.column('#2',width = 50, stretch=tk.NO)
        self.inputTree.column('#0', width = 100, stretch=tk.NO, anchor ="e")
        self.inputTreeview = self.inputTree
        self.inputTree.grid(row=11,rowspan=10, columnspan=1, column = 1, sticky='nsew')
        
        self.outputTree = ttk.Treeview( self, columns=('Type', 'Value'))
        outputvsb = ttk.Scrollbar(self, orient="vertical", command=self.outputTree.yview)
        self.outputTree.heading('#0', text='Bit')
        self.outputTree.heading('#1', text='Type')
        self.outputTree.heading('#2', text='Value')
        self.outputTree.column('#1', width = 100, stretch=tk.NO)
        self.outputTree.column('#2', width = 50, stretch=tk.NO)
        self.outputTree.column('#0', width = 100, stretch=tk.NO)
        self.outputTree.grid(row=11, rowspan=10, columnspan=1, column = 2, sticky='nsew')
        self.outputTreeview = self.outputTree
        
        self.logicTree = ttk.Treeview(self, columns=2)
        logicvsb = ttk.Scrollbar(self, orient="vertical", command=self.logicTree.yview)
        self.logicTree.heading('#0', text='Equation')
        self.logicTree.heading('#1', text='Assign To')
        self.logicTree.column('#1', width = 100, stretch=tk.YES, anchor = 'w')
        self.logicTree.column('#0', width = 500, stretch=tk.YES, anchor='e')
        self.logicTree.grid(row=1,rowspan=10, columnspan = 3, column = 1, sticky='nsew')
        self.logicTreeview = self.logicTree

        self.acceptbutton = tk.Button(self, text="Accept", command=self.acceptTest)
        self.acceptbutton.grid(column = 3, row = 12)
        
        self.cancelbutton = tk.Button(self, text="Cancel", command=self.destroy)
        self.cancelbutton.grid(column = 3, row = 14)

        self.inputTree.bind("<Double-1>", self.OnDoubleClick)             
        
		#Read all input and outputs from PLC into appropriate Treeview
        e = xml.etree.ElementTree.parse(self.filename).getroot()
        for bit in e.findall('bit'):
            bitType = bit.find('type').text
            name = bit.get('name')
            if bitType == "INPUT":
                self.inputTreeview.insert('', 'end', text=name, values=(bitType, 0))
                
            elif bitType == "OUTPUT":
                self.outputTreeview.insert('', 'end', text=name, values=(bitType, 0))
        
		#Read all logic equations from PLC into Logic Treeview        
        for logic in e.findall('logic'):
            assign = logic.find('assign').text
            equation = logic.find('equation').text
            self.logicTreeview.insert('', 'end', text=equation, values=(assign))

    def OnDoubleClick(self, event):
	#Toggles bit value when double clicked only in test state 
        if self.Test:
            if self.inputTree.item(self.inputTree.selection()[0], "values")[1] == '0':
                self.inputTree.set(self.inputTree.selection()[0],'Value','1')
            elif self.inputTree.item(self.inputTree.selection()[0], "values")[1] == '1':
                self.inputTree.set(self.inputTree.selection()[0],'Value','0')

        self.logicCheck()

    def acceptTest(self):
	#Inputs can not be changed by user and are retrieved from CTC and Track Model
        self.Test = False
        self.acceptbutton.destroy()
		
		#Sets all values to 0
        for inputs in self.inputTree.get_children():
            self.inputTree.set(inputs,'Value','0')
            
        for outputs in self.outputTree.get_children():
            self.outputTree.set(outputs,'Value','0')

        self.getInputs()
                
    def logicCheck(self):
	#Updates all outputs based on logic expressions, inputs, and output states
        algebra = boolean.BooleanAlgebra()
        for item in self.logicTree.get_children():
                logic = self.logicTree.item(item, 'text')
                assignBit = self.logicTree.item(item, 'values')[0]
                logic = logic.replace("+", "OR")
                logic = logic.replace("*", "AND")
                

                logicList = logic.split()
                 
               
		#Modifies logical expression to be evaluated by boolean library
                for n in logicList:
                    if n != "AND" or "OR":
                       
                        if n[0] =='(':
                            
                            n = n[1:]
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            n = '(' + n
                        elif n[:2] == '~(':
                            
                            n = n[2:]
                            
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic = logic.replace(n, 'True')
                            n = '~(' + n

                        elif n[0] =='~' :
                            
                            n = n[1:]
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            n = 'NOT ' + n
                        
                        elif n[len(n)-1] == ')' and n[0] =='~':
                            
                            n = n[:-1]
                            n = n[1:]
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            n = "NOT " + n + ')'    
                        elif n[len(n)-1] == ')':
                            
                            n = n[:-1]
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            n = n + ')'
                        else:
                            
                            for bit in self.inputTree.get_children():
                                if self.inputTree.item(bit, "text") == n:
                                    if self.inputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.inputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                            for bit in self.outputTree.get_children():
                                if self.outputTree.item(bit, "text") == n:
                                    if self.outputTree.item(bit, "values")[1] == '0':
                                        logic =logic.replace(n, 'False')
                                    elif self.outputTree.item(bit, "values")[1] == '1':
                                        logic =logic.replace(n, 'True')
                                                
                finalValue = algebra.parse(logic)
        
                for output in self.outputTree.get_children():
                    if self.outputTree.item(output, "text") == assignBit:
                        self.outputTree.set(output,'Value',finalValue.simplify())
        if not self.Test:
            self.writeOutputstoXML()

    def writeOutputstoXML(self):
	#writes outputs to XML for CTC and Track Model to read
        try:
            if (os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackControllerOutputs.xml")):
                xmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackControllerOutputs.xml")
                root = xmlFile.getroot()

                for outputs in self.outputTree.get_children():
                    found = False
                    for bits in root.findall('bit'):                  
                        if bits.attrib['name'] == self.outputTree.item(outputs, "text"):
                            bits.text = self.outputTree.item(outputs, 'values')[1]
                            found = True
                    if (found == False):
                        xml.etree.ElementTree.SubElement(root,"bit", name = self.outputTree.item(outputs, "text")).text = self.outputTree.item(outputs, 'values')[1]
            else:
                root = xml.etree.ElementTree.Element("bits")
                for outputs in self.outputTree.get_children():
                    xml.etree.ElementTree.SubElement(root,"bit", name = self.outputTree.item(outputs, "text")).text = self.outputTree.item(outputs, 'values')[1]
                    
            tree = xml.etree.ElementTree.ElementTree(root)
            tree.write(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackControllerOutputs.xml")
        except:
            print("Outputs file not found")
    def getInputs(self):
	#Read inputs from CTC abd Track Model XML
        if (os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\CTCOutput.xml") and os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")):
            try:
                CTCxmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\CTCOutput.xml")
                CTCroot = CTCxmlFile.getroot()
                TMxmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")
                TMroot = TMxmlFile.getroot()
                
                for inputs in self.inputTree.get_children():
                    found = False
                    for bits in CTCroot.findall('bit'):
                        if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                            self.inputTree.set(inputs,'Value', bits.text)
                            found = True
                    for bits in TMroot.findall('bit'):
                        if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                            self.inputTree.set(inputs,'Value', bits.text)
                            found = True
                    if (found == False):
                        self.inputTree.set(inputs,'Value','0')
            except:
                try:
                    CTCxmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\CTCOutput.xml")
                    CTCroot = CTCxmlFile.getroot()
                    for inputs in self.inputTree.get_children():
                        found = False
                        for bits in CTCroot.findall('bit'):
                            if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                                self.inputTree.set(inputs,'Value', bits.text)
                                found = True
                        if (found == False):
                            self.inputTree.set(inputs,'Value','0')
                except:
                    print("CTC Inputs cannot be read.")
                    try:
                        TMxmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")
                        TMroot = TMxmlFile.getroot()
                        
                        for inputs in self.inputTree.get_children():
                            found = False
                            for bits in TMroot.findall('bit'):
                                if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                                    self.inputTree.set(inputs,'Value', bits.text)
                                    found = True
                            if (found == False):
                                self.inputTree.set(inputs,'Value','0')
                    except:
                        print("Track Model Inputs cannot be read.")
                    
        elif(os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\CTCOutput.xml") and not os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")):
            print("Track Model XML not found")
            CTCxmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\\xml\CTCOutput.xml")
            CTCroot = CTCxmlFile.getroot()

            for inputs in self.inputTree.get_children():
                found = False
                for bits in CTCroot.findall('bit'):
                    if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                        self.inputTree.set(inputs,'Value', bits.text)
                        found = True
                if (found == False):
                    self.inputTree.set(inputs,'Value','0')
             
        elif(not os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\CTCOutput.xml") and os.path.isfile(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")):
            print("CTC XML not found")
            TMxmlFile = xml.etree.ElementTree.parse(str(Path(os.path.abspath(__file__)).parents[0])+"\\xml\TrackModelOutputs.xml")
            TMroot = TMxmlFile.getroot()

            for inputs in self.inputTree.get_children():
                found = False
                for bits in TMroot.findall('bit'):
                    if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                        self.inputTree.set(inputs,'Value', bits.text)
                        found = True
                if (found == False):
                    self.inputTree.set(inputs,'Value','0')
        else:
            print("CTC XML and Track Model XML not found")
            for inputs in self.inputTree.get_children():   
                self.inputTree.set(inputs,'Value','0')

        self.logicCheck()

        self.after(500, self.getInputs)

if __name__ == "__main__":
    root = tk.Tk()
    root.title("Track Controller")
    main = MainWindow(root)
    root.mainloop()
