import tkinter as tk
from tkinter import ttk
from tkinter.scrolledtext import ScrolledText
from tkinter.scrolledtext import Scrollbar
from tkinter.filedialog import askopenfilename
import xml.etree.ElementTree
import os
import csv
import time


# Main Window
class MainWindow(tk.Frame):
    # load file
    def load(self):
        filename = askopenfilename()

        # make sure file is csv later
        with open(filename, 'r') as file:
            csv_reader = csv.reader(file, delimiter=',')
            i = 0
            numSwitch = 1
            numSig = 0
            st = ''
            sw = 0
            holdr = 0
            rc = ''
            stations = []
            for r in csv_reader:
                if i == 0:
                    i += 1
                else:
                    holdr = r
                    ug = ''
                    sw = 0
                    st = ''
                    rc = ''
                    if r[0] == "Red":
                        infr = r[6]
                        if "UNDERGROUND" in infr:
                            ug = 'underground'
                        if "SWITCH" in infr:
                            sw = numSwitch
                            numSwitch += 1

                        if "STATION" in infr:
                            st = "STATION"
                        if "PIONEER" in infr:
                            st = "PIONEER"
                        elif "EDGEBROOK" in infr:
                            st = "EDGEBROOK"
                        elif "WHITED" in infr:
                            st = "WHITED"
                        elif "SOUTH BANK" in infr:
                            st = "SOUTH BANK"
                        elif "CENTRAL" in infr:
                            st = "CENTRAL"
                        elif "INGLEWOOD" in infr:
                            st = "INGLEWOOD"
                        elif "OVERBROOK" in infr:
                            st = "OVERBROOK"
                        elif "GLENBURY" in infr:
                            st = "GLENBURY"
                        elif "DORMONT" in infr:
                            st = "DORMONT"
                        elif "MT LEBANON" in infr:
                            st = "MT LEBANON"
                        elif "POPLAR" in infr:
                            st = "POPLAR"
                        elif "CASTLE SHANNON" in infr:
                            st = "CASTLE SHANNON"
                        elif "SHADYSIDE" in infr:
                            st = "SHADYSIDE"
                        elif "HERRON AVE" in infr:
                            st = "HERRON AVE"
                        elif "SWISSVILLE" in infr:
                            st = "SWISSVILLE"
                        elif "PENN STATION" in infr:
                            st = "PENN STATION"
                        elif "STEEL PLAZA" in infr:
                            st = "STEEL PLAZA"
                        elif "FIRST AVE" in infr:
                            st = "FIRST AVE"
                        elif "STATION SQUARE" in infr:
                            st = "STATION SQUARE"
                        elif "SOUTH HILLS JUNCTION" in infr:
                            st = "SOUTH HILLS JUNCTION"
                        if rc == 0:
                            rc = ''
                        if sw == 0:
                            self.red_table.insert('', 'end',
                                                  values=(r[1], r[2], r[3], r[4], r[5], ug, st, '', r[16], r[8],
                                                          r[11],
                                                          r[10], 'false', 'off', 'unoccupied', 'working',
                                                          'working', 'working'))
                        else:
                            self.red_table.insert('', 'end',
                                                  values=(r[1], r[2], r[3], r[4], r[5], ug, st, sw, r[16], r[8], r[11],
                                                          r[10], 'false', 'off', 'unoccupied', 'working',
                                                          'working', 'working'))

                    if r[0] == "Green":
                        infr = r[6]
                        sw = 0
                        if "UNDERGROUND" in infr:
                            ug = 'underground'
                        if "SWITCH" in infr:
                            sw = numSwitch
                            numSwitch += 1
                        if "RAILWAY CROSSING" in infr:
                            rc = "railway crossing"
                        if "STATION" in infr:
                            st = "STATION"
                        if "PIONEER" in infr:
                            st = "PIONEER"
                        elif "EDGEBROOK" in infr:
                            st = "EDGEBROOK"
                        elif "WHITED" in infr:
                            st = "WHITED"
                        elif "SOUTH BANK" in infr:
                            st = "SOUTH BANK"
                        elif "CENTRAL" in infr:
                            st = "CENTRAL"
                        elif "INGLEWOOD" in infr:
                            st = "INGLEWOOD"
                        elif "OVERBROOK" in infr:
                            st = "OVERBROOK"
                        elif "GLENBURY" in infr:
                            st = "GLENBURY"
                        elif "DORMONT" in infr:
                            st = "DORMONT"
                        elif "MT LEBANON" in infr:
                            st = "MT LEBANON"
                        elif "POPLAR" in infr:
                            st = "POPLAR"
                        elif "CASTLE SHANNON" in infr:
                            st = "CASTLE SHANNON"
                        elif "SHADYSIDE" in infr:
                            st = "SHADYSIDE"
                        elif "HERRON AVE" in infr:
                            st = "HERRON AVE"
                        elif "SWISSVILLE" in infr:
                            st = "SWISSVILLE"
                        elif "PENN STATION" in infr:
                            st = "PENN STATION"
                        elif "STEEL PLAZA" in infr:
                            st = "STEEL PLAZA"
                        elif "FIRST AVE" in infr:
                            st = "FIRST AVE"
                        elif "STATION SQUARE" in infr:
                            st = "STATION SQUARE"
                        elif "SOUTH HILLS JUNCTION" in infr:
                            st = "SOUTH HILLS JUNCTION"

                        if sw == 0:
                            self.green_table.insert('', 'end',
                                                    values=(r[1], r[2], r[3], r[4], r[5], ug, st, '', r[16], r[8],
                                                            r[11],
                                                            r[10], 'false', 'off', 'unoccupied', 'working',
                                                            'working', 'working'))
                        else:
                            self.green_table.insert('', 'end',
                                                    values=(
                                                    r[1], r[2], r[3], r[4], r[5], ug, st, sw, r[16], r[8], r[11],
                                                    r[10], 'false', 'off', 'unoccupied', 'working',
                                                    'working', 'working'))

                    if st != '':
                        if st not in stations:
                            stations.append(st)
                            self.station_table.insert('', 'end', values=(st, 0))

                    if sw != 0:
                        self.switch_table.insert('', 'end',
                                                 values=(sw, 'normal', holdr[12], holdr[13], holdr[14], holdr[15]))

                    if r[16] != '':
                        self.cross_table.insert('', 'end', values=(r[16], 'up'))

    # Power Failure
    def power_failure(self):
        if self.red_table.set(self.red_table.focus(), column='Power Failure') == 'FAILED':
            self.red_table.set(self.red_table.focus(), column='Power Failure', value='working')
        else:
            self.red_table.set(self.red_table.focus(), column='Power Failure', value='FAILED')

        if self.green_table.set(self.green_table.focus(), column='Power Failure') == 'FAILED':
            self.green_table.set(self.green_table.focus(), column='Power Failure', value='working')
        else:
            self.green_table.set(self.green_table.focus(), column='Power Failure', value='FAILED')

        self.selection_clear()

    def break_rail(self):
        if self.red_table.set(self.red_table.focus(), column='Broken Rail') == 'BROKEN':
            self.red_table.set(self.red_table.focus(), column='Broken Rail', value='working')
        else:
            self.red_table.set(self.red_table.focus(), column='Broken Rail', value='BROKEN')

        if self.green_table.set(self.green_table.focus(), column='Broken Rail') == 'BROKEN':
            self.green_table.set(self.green_table.focus(), column='Broken Rail', value='working')
        else:
            self.green_table.set(self.green_table.focus(), column='Broken Rail', value='BROKEN')

        self.selection_clear()

    def tc_fail(self):
        if self.red_table.set(self.red_table.focus(), column='TC Failure') == 'FAILED':
            self.red_table.set(self.red_table.focus(), column='TC Failure', value='working')
        else:
            self.red_table.set(self.red_table.focus(), column='TC Failure', value='FAILED')

        if self.green_table.set(self.green_table.focus(), column='TC Failure') == 'FAILED':
            self.green_table.set(self.green_table.focus(), column='TC Failure', value='working')
        else:
            self.green_table.set(self.green_table.focus(), column='TC Failure', value='FAILED')

        self.selection_clear()

    def data_update(self):
        self.write_xml()
        self.read_xml()

    def write_xml(self):
        if os.path.isfile(os.getcwd()+"\outTrackmodel.xml"):
            xfile = xml.etree.ElementTree.parse(os.getcwd()+"\outTrackmodel.xml")
            root = xfile.getroot()

            for outputs in self.red_table.get_children():
                print(self.redtree.item(outputs)['values'])
        else:
            root = xml.etree.ElementTree.Element("bits")
            for outs in self.redtree.get_children():
                i = 1
                name_build = "R"
                track_number = self.redtree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                print(name_build)
                if self.redtree.item(outs, 'values')[14] == "occupied":
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                 name=name_build).text = '0'
                else:
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                     name=name_build).text = '1'
                i += 1
            for outs in self.greentree.get_children():
                i = 1
                name_build = "G"
                track_number = self.greentree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                print(name_build)
                if self.greentree.item(outs, 'values')[14] == "occupied":
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                 name=name_build).text = '0'
                else:
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                     name=name_build).text = '1'
                i += 1

        tree = xml.etree.ElementTree.ElementTree(root)
        tree.write("outTrackmodel.xml")

    def read_xml(self):
        print("READ")

    def __init__(self, master, *args, **kwargs):
        self.master = master
        master.title("Track Model UI")
        tk.Frame.__init__(self, *args, **kwargs)
        menubar = tk.Menu(master)
        filemenu = tk.Menu(menubar, tearoff=0)
        filemenu.add_command(label="Load CSV", command=self.load)
        menubar.add_cascade(label="File", menu=filemenu)
        root.config(menu=menubar)

        self.label = tk.Label(master, text='Red Line')
        self.label.grid(row=0, column=0, sticky='nsw')

        self.red_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                 'Underground', 'Station', 'Switch', 'railway x-ing',
                                                                 'Elevation',
                                                                 'Direction', 'Bidirectional',
                                                                 'Track Block', 'Track Heater', 'Occupation',
                                                                 'Power Failure', 'TC Failure', 'Broken Rail'),
                                      selectmode='extended')
        self.red_table.heading('#1', text='Section', anchor=tk.CENTER)
        self.red_table.heading('#2', text='Block', anchor=tk.CENTER)
        self.red_table.heading('#3', text='Length', anchor=tk.CENTER)
        self.red_table.heading('#4', text='Grade', anchor=tk.CENTER)
        self.red_table.heading('#5', text='Speed', anchor=tk.CENTER)
        self.red_table.heading('#6', text='Underground', anchor=tk.CENTER)
        self.red_table.heading('#7', text='Station', anchor=tk.CENTER)
        self.red_table.heading('#8', text='Switch', anchor=tk.CENTER)
        self.red_table.heading('#9', text='Railway x-ing', anchor=tk.CENTER)
        self.red_table.heading('#10', text='Elevation', anchor=tk.CENTER)
        self.red_table.heading('#11', text='Direction', anchor=tk.CENTER)
        self.red_table.heading('#12', text='Bidirectional', anchor=tk.CENTER)
        self.red_table.heading('#13', text='Track Block', anchor=tk.CENTER)
        self.red_table.heading('#14', text='Track Heater', anchor=tk.CENTER)
        self.red_table.heading('#15', text='Occupation', anchor=tk.CENTER)
        self.red_table.heading('#16', text='Power Failure', anchor=tk.CENTER)
        self.red_table.heading('#17', text='TC Failure', anchor=tk.CENTER)
        self.red_table.heading('#18', text='Broken Rail', anchor=tk.CENTER)
        self.red_table.column('#1', stretch=tk.YES, width=50)
        self.red_table.column('#2', stretch=tk.YES, width=40)
        self.red_table.column('#3', stretch=tk.YES, width=50)
        self.red_table.column('#4', stretch=tk.YES, width=50)
        self.red_table.column('#5', stretch=tk.YES, width=50)
        self.red_table.column('#6', stretch=tk.YES, width=80)
        self.red_table.column('#7', stretch=tk.YES, width=140)
        self.red_table.column('#8', stretch=tk.YES, width=50)
        self.red_table.column('#9', stretch=tk.YES, width=90)
        self.red_table.column('#10', stretch=tk.YES, width=60)
        self.red_table.column('#11', stretch=tk.YES, width=60)
        self.red_table.column('#12', stretch=tk.YES, width=90)
        self.red_table.column('#13', stretch=tk.YES, width=90)
        self.red_table.column('#14', stretch=tk.YES, width=80)
        self.red_table.column('#15', stretch=tk.YES, width=90)
        self.red_table.column('#16', stretch=tk.YES, width=80)
        self.red_table.column('#17', stretch=tk.YES, width=90)
        self.red_table.column('#18', stretch=tk.YES, width=90)
        self.red_table['show'] = 'headings'
        self.red_table.grid(row=1, column=0, columnspan=20, padx=5, pady=5, sticky='nsew')
        self.redtree = self.red_table

        self.glabel = tk.Label(master, text='Green Line')
        self.glabel.grid(row=2, column=0, columnspan=1, sticky='sw')

        self.green_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                   'Underground', 'Station', 'Switch', 'railway x-ing',
                                                                   'Elevation',
                                                                   'Direction', 'Bidirectional',
                                                                   'Track Block', 'Track Heater', 'Occupation',
                                                                   'Power Failure', 'TC Failure', 'Broken Rail'),
                                        selectmode='extended')
        self.green_table.heading('#1', text='Section', anchor=tk.CENTER)
        self.green_table.heading('#2', text='Block', anchor=tk.CENTER)
        self.green_table.heading('#3', text='Length', anchor=tk.CENTER)
        self.green_table.heading('#4', text='Grade', anchor=tk.CENTER)
        self.green_table.heading('#5', text='Speed', anchor=tk.CENTER)
        self.green_table.heading('#6', text='Underground', anchor=tk.CENTER)
        self.green_table.heading('#7', text='Station', anchor=tk.CENTER)
        self.green_table.heading('#8', text='Switch', anchor=tk.CENTER)
        self.green_table.heading('#9', text='Railway x-ing', anchor=tk.CENTER)
        self.green_table.heading('#10', text='Elevation', anchor=tk.CENTER)
        self.green_table.heading('#11', text='Direction', anchor=tk.CENTER)
        self.green_table.heading('#12', text='Bidirectional', anchor=tk.CENTER)
        self.green_table.heading('#13', text='Track Block', anchor=tk.CENTER)
        self.green_table.heading('#14', text='Track Heater', anchor=tk.CENTER)
        self.green_table.heading('#15', text='Occupation', anchor=tk.CENTER)
        self.green_table.heading('#16', text='Power Failure', anchor=tk.CENTER)
        self.green_table.heading('#17', text='TC Failure', anchor=tk.CENTER)
        self.green_table.heading('#18', text='Broken Rail', anchor=tk.CENTER)
        self.green_table.column('#1', stretch=tk.YES, width=50)
        self.green_table.column('#2', stretch=tk.YES, width=40)
        self.green_table.column('#3', stretch=tk.YES, width=50)
        self.green_table.column('#4', stretch=tk.YES, width=50)
        self.green_table.column('#5', stretch=tk.YES, width=50)
        self.green_table.column('#6', stretch=tk.YES, width=80)
        self.green_table.column('#7', stretch=tk.YES, width=140)
        self.green_table.column('#8', stretch=tk.YES, width=50)
        self.green_table.column('#9', stretch=tk.YES, width=90)
        self.green_table.column('#10', stretch=tk.YES, width=60)
        self.green_table.column('#11', stretch=tk.YES, width=60)
        self.green_table.column('#12', stretch=tk.YES, width=90)
        self.green_table.column('#13', stretch=tk.YES, width=90)
        self.green_table.column('#14', stretch=tk.YES, width=80)
        self.green_table.column('#15', stretch=tk.YES, width=90)
        self.green_table.column('#16', stretch=tk.YES, width=80)
        self.green_table.column('#17', stretch=tk.YES, width=90)
        self.green_table.column('#18', stretch=tk.YES, width=90)
        self.green_table['show'] = 'headings'
        self.green_table.grid(row=3, column=0, columnspan=20, padx=5, pady=5)
        self.greentree = self.green_table

        self.slabel = tk.Label(master, text='Switches, Crossing, Signals, and Murphy')
        self.slabel.grid(row=4, column=0, columnspan=1, sticky='sw')

        self.switch_table = ttk.Treeview(master, height=7, columns=('Switch', 'Position', 'Forward Normal',
                                                                    'Reverse Normal', 'Forward Reverse',
                                                                    'Reverse Reverse'), selectmode='extended')
        self.switch_table.heading('#1', text='Switch', anchor=tk.CENTER)
        self.switch_table.heading('#2', text='Position', anchor=tk.CENTER)
        self.switch_table.heading('#3', text='Forward Normal', anchor=tk.CENTER)
        self.switch_table.heading('#4', text='Reverse Normal', anchor=tk.CENTER)
        self.switch_table.heading('#5', text='Forward Reverse', anchor=tk.CENTER)
        self.switch_table.heading('#6', text='Reverse Reverse', anchor=tk.CENTER)
        self.switch_table.column('#1', stretch=tk.YES, width=60)
        self.switch_table.column('#2', stretch=tk.YES, width=60)
        self.switch_table.column('#3', stretch=tk.YES, width=100)
        self.switch_table.column('#4', stretch=tk.YES, width=100)
        self.switch_table.column('#5', stretch=tk.YES, width=100)
        self.switch_table.column('#6', stretch=tk.YES, width=100)
        self.switch_table['show'] = 'headings'
        self.switch_table.columnconfigure(0, weight=1)
        self.switch_table.grid(row=5, column=0, rowspan=6, columnspan=7, padx=5, pady=5, sticky='nsew')
        self.switchtree = self.switch_table

        self.cross_table = ttk.Treeview(master, height=7, columns=('Crossing', 'Position'), selectmode='extended')
        self.cross_table.heading('#1', text='Crossing', anchor=tk.CENTER)
        self.cross_table.heading('#2', text='Position', anchor=tk.CENTER)
        self.cross_table.column('#1', stretch=tk.YES, width=60)
        self.cross_table.column('#2', stretch=tk.YES, width=60)
        self.cross_table['show'] = 'headings'
        self.cross_table.columnconfigure(1, weight=1)
        self.cross_table.grid(row=5, column=13, rowspan=6, padx=5, pady=5, sticky='nsew')
        self.crosstree = self.cross_table

        self.signal_table = ttk.Treeview(master, height=7, columns=('Signal', 'Color'), selectmode='extended')
        self.signal_table.heading('#1', text='Signal', anchor=tk.CENTER)
        self.signal_table.heading('#2', text='Color', anchor=tk.CENTER)
        self.signal_table.column('#1', stretch=tk.YES, width=60)
        self.signal_table.column('#2', stretch=tk.YES, width=60)
        self.signal_table['show'] = 'headings'
        self.signal_table.grid(row=5, column=15, rowspan=6, padx=5, pady=5, sticky='nsew')
        self.signaltree = self.signal_table

        self.station_table = ttk.Treeview(master, height=7, columns=('Station', 'Passenger Count'),
                                          selectmode='extended')
        self.station_table.heading('#1', text='Station', anchor=tk.CENTER)
        self.station_table.heading('#2', text='Passenger Count', anchor=tk.CENTER)
        self.station_table.column('#1', stretch=tk.YES, width=140)
        self.station_table.column('#2', stretch=tk.YES, width=100)
        self.station_table['show'] = 'headings'
        self.station_table.grid(row=5, rowspan=6, column=17, padx=5, pady=5, sticky='nsew')
        self.stationtree = self.station_table

        self.pf_button = tk.Button(master, text="Power Failure", command=self.power_failure)
        self.pf_button.grid(row=5, padx=5, pady=5, column=18, sticky="nsew")
        self.tc_button = tk.Button(master, text="TC Failure", command=self.tc_fail)
        self.tc_button.grid(row=6, padx=5, pady=5, column=18, sticky="nsew")
        self.br_button = tk.Button(master, text="Broken Rail", command=self.break_rail)
        self.br_button.grid(row=7, padx=5, pady=5, column=18, sticky="nsew")
        self.du_button = tk.Button(master, text="Start", command=self.data_update)
        self.du_button.grid(row=8, padx=5, pady=5, column=18, sticky="nsew")

        self.columnconfigure(2, weight=1)  # column with treeview
        self.rowconfigure(2, weight=1)  # row with treeview

        self.config()

    @staticmethod
    def greet():
        print("Greetings!")


root = tk.Tk()
root.config()
my_gui = MainWindow(root)
root.mainloop()
