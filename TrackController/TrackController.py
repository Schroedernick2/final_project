try:
	from tkinter import ttk
	import tkinter as tk
	from tkinter.scrolledtext import ScrolledText
	from tkinter import filedialog
except ImportError:
	import Tkinter as tk
	import ttk
import xml.etree.ElementTree
import os
import boolean

class MainWindow(tk.Frame):
    tabcounter = 1
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
        root.geometry("860x480")
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
        tab = PanelFrame(self.tab_frame)
        try:
            self.tab_frame.add(tab, text = self.tabcounter)
            self.tabcounter += 1
        except:
            print("No file selected")        
class PanelFrame(ttk.Frame):
    Test = True
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
        self.filename = filedialog.askopenfilename(initialdir = "/",title = "Select file",filetypes = (("XML","*.xml"),("All Files","*.*")))
        if self.filename != '':
            print ("Logic file has been loaded")
            self.testPanel()
        else:
            self.destroy()        
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
        self.inputTree.grid(row=1,rowspan=10, columnspan=2, column = 1, sticky='nsew')
        

        self.outputTree = ttk.Treeview( self, columns=('Type', 'Value'))
        outputvsb = ttk.Scrollbar(self, orient="vertical", command=self.outputTree.yview)
        self.outputTree.heading('#0', text='Bit')
        self.outputTree.heading('#1', text='Type')
        self.outputTree.heading('#2', text='Value')
        self.outputTree.column('#1', width = 100, stretch=tk.NO)
        self.outputTree.column('#2', width = 50, stretch=tk.NO)
        self.outputTree.column('#0', width = 100, stretch=tk.NO)
        self.outputTree.grid(row=11, rowspan=10, columnspan=2, column = 1, sticky='nsew')
        self.outputTreeview = self.outputTree
        
        
        self.logicTree = ttk.Treeview(self, columns=2)
        logicvsb = ttk.Scrollbar(self, orient="vertical", command=self.logicTree.yview)
        self.logicTree.heading('#0', text='Equation')
        self.logicTree.heading('#1', text='Assign To')
        self.logicTree.column('#1', width = 100, stretch=tk.YES, anchor = 'w')
        self.logicTree.column('#0', width = 500, stretch=tk.YES, anchor='e')
        self.logicTree.grid(row=1,rowspan=10, column = 3, sticky='nsew')
        self.logicTreeview = self.logicTree

        self.acceptbutton = tk.Button(self, text="Accept", command=self.acceptTest)
        self.acceptbutton.grid(column = 3, row = 12)
        
        self.cancelbutton = tk.Button(self, text="Cancel", command=self.destroy)
        self.cancelbutton.grid(column = 3, row = 14)

        self.inputTree.bind("<Double-1>", self.OnDoubleClick)             
        
        e = xml.etree.ElementTree.parse(self.filename).getroot()
        for bit in e.findall('bit'):
            bitType = bit.find('type').text
            name = bit.get('name')
            if bitType == "INPUT":
                self.inputTreeview.insert('', 'end', text=name, values=(bitType, 0))
                
            elif bitType == "OUTPUT":
                self.outputTreeview.insert('', 'end', text=name, values=(bitType, 0))
                
        for logic in e.findall('logic'):
            assign = logic.find('assign').text
            equation = logic.find('equation').text
            self.logicTreeview.insert('', 'end', text=equation, values=(assign))

    def OnDoubleClick(self, event):
        if self.Test:
            if self.inputTree.item(self.inputTree.selection()[0], "values")[1] == '0':
                self.inputTree.set(self.inputTree.selection()[0],'Value','1')
            elif self.inputTree.item(self.inputTree.selection()[0], "values")[1] == '1':
                self.inputTree.set(self.inputTree.selection()[0],'Value','0')

        self.logicCheck()

    def acceptTest(self):
        self.Test = False
        self.acceptbutton.destroy()

        for inputs in self.inputTree.get_children():
            self.inputTree.set(inputs,'Value','0')
            
        for outputs in self.outputTree.get_children():
            self.outputTree.set(outputs,'Value','0')

        self.getInputs()
                
    def logicCheck(self):
        algebra = boolean.BooleanAlgebra()
        for item in self.logicTree.get_children():
                logic = self.logicTree.item(item, 'text')
                assignBit = self.logicTree.item(item, 'values')[0]
                logic = logic.replace("+", "OR")
                logic = logic.replace("*", "AND")

                logicList = logic.split()
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
                        
        self.writeOutputstoXML()

    def writeOutputstoXML(self):
        if (os.path.isfile(os.getcwd()+"\TrackControllerOutputs.xml")):
            xmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\TrackControllerOutputs.xml")
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
        tree.write("TrackControllerOutputs.xml")
    def getInputs(self):
        if (os.path.isfile(os.getcwd()+"\CTCOutputs.xml") and os.path.isfile(os.getcwd()+"\TrackModelOutputs.xml")):
            CTCxmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\CTCOutputs.xml")
            CTCroot = CTCxmlFile.getroot()

            TMxmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\TrackModelOutputs.xml")
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
                    print(self.inputTree.item(inputs, "text")+ "was not found")
                    self.inputTree.set(inputs,'Value','0')
        elif(os.path.isfile(os.getcwd()+"\CTCOutputs.xml") and not os.path.isfile(os.getcwd()+"\TrackModelOutputs.xml")):
            print("Track Model XML not found")
            CTCxmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\CTCOutputs.xml")
            CTCroot = CTCxmlFile.getroot()

            for inputs in self.inputTree.get_children():
                found = False
                for bits in CTCroot.findall('bit'):
                    if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                        self.inputTree.set(inputs,'Value', bits.text)
                        found = True
                if (found == False):
                    print(self.inputTree.item(inputs, "text")+ "was not found")
                    self.inputTree.set(inputs,'Value','0')
             
        elif(not os.path.isfile(os.getcwd()+"\CTCOutputs.xml") and os.path.isfile(os.getcwd()+"\TrackModelOutputs.xml")):
            print("CTC XML not found")
            TMxmlFile = xml.etree.ElementTree.parse(os.getcwd()+"\TrackModelOutputs.xml")
            TMroot = TMxmlFile.getroot()

            for inputs in self.inputTree.get_children():
                found = False
                for bits in TMroot.findall('bit'):
                    if bits.attrib['name'] == self.inputTree.item(inputs, "text"):
                        self.inputTree.set(inputs,'Value', bits.text)
                        found = True
                if (found == False):
                    print(self.inputTree.item(inputs, "text")+ "was not found")
                    self.inputTree.set(inputs,'Value','0')
        else:
            print("CTC XML and Track Model XML not found")
            for inputs in self.inputTree.get_children():   
                self.inputTree.set(inputs,'Value','0')

        self.logicCheck()

        self.after(1000, self.getInputs)

if __name__ == "__main__":
    root = tk.Tk()
    main = MainWindow(root)
    root.mainloop()
