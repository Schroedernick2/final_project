import tkinter as tk
from tkinter import ttk
from tkinter.scrolledtext import ScrolledText
from tkinter.scrolledtext import Scrollbar
import xml.etree.ElementTree
import os


# Main Window
class MainWindow(tk.Frame):
    def __init__(self, master, *args, **kwargs):
        self.master = master
        master.title("Track Model UI")
        tk.Frame.__init__(self, *args, **kwargs)

        self.menubar = tk.Menu(root)
        self.filemenu = tk.Menu(self.menubar, tearoff=0)
        self.filemenu.add_command(label="File")
        self.menubar.add_cascade(label="Load CSV File", menu=self.filemenu)

        self.label = tk.Label(master, text='Red Line')
        self.label.grid(row=1, column=1, sticky='nsw')

        self.red_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                 'Underground', 'Station', 'Switch', 'Elevation',
                                                                 'Direction', 'Bidirectional', 'Power Failure',
                                                                 'Track Block', 'Track Heater', 'Occupation',
                                                                 'Power Failure', 'TC Failure', 'Broken Rail'),
                                      selectmode='extended')
        self.red_table.heading('#0', text='Section', anchor=tk.CENTER)
        self.red_table.heading('#1', text='Block', anchor=tk.CENTER)
        self.red_table.heading('#2', text='Length', anchor=tk.CENTER)
        self.red_table.heading('#3', text='Grade', anchor=tk.CENTER)
        self.red_table.heading('#4', text='Speed', anchor=tk.CENTER)
        self.red_table.heading('#5', text='Underground', anchor=tk.CENTER)
        self.red_table.heading('#6', text='Station', anchor=tk.CENTER)
        self.red_table.heading('#7', text='Switch', anchor=tk.CENTER)
        self.red_table.heading('#8', text='Elevation', anchor=tk.CENTER)
        self.red_table.heading('#9', text='Direction', anchor=tk.CENTER)
        self.red_table.heading('#10', text='Bidirectional', anchor=tk.CENTER)
        self.red_table.heading('#11', text='Power Failure', anchor=tk.CENTER)
        self.red_table.heading('#12', text='Track Block', anchor=tk.CENTER)
        self.red_table.heading('#13', text='Track Heater', anchor=tk.CENTER)
        self.red_table.heading('#14', text='Occupation', anchor=tk.CENTER)
        self.red_table.heading('#15', text='Power Failure', anchor=tk.CENTER)
        self.red_table.heading('#16', text='TC Failure', anchor=tk.CENTER)
        self.red_table.heading('#17', text='Broken Rail', anchor=tk.CENTER)
        self.red_table.column('#0', stretch=tk.YES, width=60)
        self.red_table.column('#1', stretch=tk.YES, width=60)
        self.red_table.column('#2', stretch=tk.YES, width=60)
        self.red_table.column('#3', stretch=tk.YES, width=60)
        self.red_table.column('#4', stretch=tk.YES, width=60)
        self.red_table.column('#5', stretch=tk.YES, width=80)
        self.red_table.column('#6', stretch=tk.YES, width=60)
        self.red_table.column('#7', stretch=tk.YES, width=80)
        self.red_table.column('#8', stretch=tk.YES, width=60)
        self.red_table.column('#9', stretch=tk.YES, width=60)
        self.red_table.column('#10', stretch=tk.YES, width=90)
        self.red_table.column('#11', stretch=tk.YES, width=90)
        self.red_table.column('#12', stretch=tk.YES, width=80)
        self.red_table.column('#13', stretch=tk.YES, width=90)
        self.red_table.column('#14', stretch=tk.YES, width=80)
        self.red_table.column('#15', stretch=tk.YES, width=90)
        self.red_table.column('#16', stretch=tk.YES, width=90)
        self.red_table.column('#17', stretch=tk.YES, width=90)
        self.red_table.grid(row=3, column=1, padx=5, pady=5, sticky='nsew')
        self.treeview = self.red_table

        self.glabel = tk.Label(master, text='Green Line')
        self.glabel.grid(row=20, column=1, columnspan=1, sticky='sw')

        self.green_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                   'Underground', 'Station', 'Switch', 'Elevation',
                                                                   'Direction', 'Bidirectional', 'Power Failure',
                                                                   'Track Block', 'Track Heater', 'Occupation',
                                                                   'Power Failure', 'TC Failure', 'Broken Rail'),
                                        selectmode='extended')
        self.green_table.heading('#0', text='Section', anchor=tk.CENTER)
        self.green_table.heading('#1', text='Block', anchor=tk.CENTER)
        self.green_table.heading('#2', text='Length', anchor=tk.CENTER)
        self.green_table.heading('#3', text='Grade', anchor=tk.CENTER)
        self.green_table.heading('#4', text='Speed', anchor=tk.CENTER)
        self.green_table.heading('#5', text='Underground', anchor=tk.CENTER)
        self.green_table.heading('#6', text='Station', anchor=tk.CENTER)
        self.green_table.heading('#7', text='Switch', anchor=tk.CENTER)
        self.green_table.heading('#8', text='Elevation', anchor=tk.CENTER)
        self.green_table.heading('#9', text='Direction', anchor=tk.CENTER)
        self.green_table.heading('#10', text='Bidirectional', anchor=tk.CENTER)
        self.green_table.heading('#11', text='Power Failure', anchor=tk.CENTER)
        self.green_table.heading('#12', text='Track Block', anchor=tk.CENTER)
        self.green_table.heading('#13', text='Track Heater', anchor=tk.CENTER)
        self.green_table.heading('#14', text='Occupation', anchor=tk.CENTER)
        self.green_table.heading('#15', text='Power Failure', anchor=tk.CENTER)
        self.green_table.heading('#16', text='TC Failure', anchor=tk.CENTER)
        self.green_table.heading('#17', text='Broken Rail', anchor=tk.CENTER)
        self.green_table.column('#0', stretch=tk.YES, width=60)
        self.green_table.column('#1', stretch=tk.YES, width=60)
        self.green_table.column('#2', stretch=tk.YES, width=60)
        self.green_table.column('#3', stretch=tk.YES, width=60)
        self.green_table.column('#4', stretch=tk.YES, width=60)
        self.green_table.column('#5', stretch=tk.YES, width=80)
        self.green_table.column('#6', stretch=tk.YES, width=60)
        self.green_table.column('#7', stretch=tk.YES, width=80)
        self.green_table.column('#8', stretch=tk.YES, width=60)
        self.green_table.column('#9', stretch=tk.YES, width=60)
        self.green_table.column('#10', stretch=tk.YES, width=90)
        self.green_table.column('#11', stretch=tk.YES, width=90)
        self.green_table.column('#12', stretch=tk.YES, width=80)
        self.green_table.column('#13', stretch=tk.YES, width=90)
        self.green_table.column('#14', stretch=tk.YES, width=80)
        self.green_table.column('#15', stretch=tk.YES, width=90)
        self.green_table.column('#16', stretch=tk.YES, width=90)
        self.green_table.column('#17', stretch=tk.YES, width=90)
        self.green_table.grid(row=22, column=1, padx=5, pady=5)
        self.treeview = self.green_table

        self.switch_table = ttk.Treeview(master, height=7, columns=('Switch', 'Position', 'Forward Track Circuit',
                                                                    'Reverse Track Circuit'), selectmode='extended')
        self.switch_table.heading('#0', text='Switch', anchor=tk.CENTER)
        self.switch_table.heading('#1', text='Position', anchor=tk.CENTER)
        self.switch_table.heading('#2', text='Forward Track Circuit', anchor=tk.CENTER)
        self.switch_table.heading('#3', text='Reverse Track Circuit', anchor=tk.CENTER)
        self.switch_table.column('#0')
        self.switch_table.column('#1')
        self.switch_table.column('#2')
        self.switch_table.column('#3')
        self.switch_table.grid(row=25, column=1, columnspan=1, padx=5, pady=5, sticky='nsew')
        self.treeview = self.switch_table

        self.cross_table = ttk.Treeview(master, height=7, columns=('Crossing', 'Position'), selectmode='extended')
        self.cross_table.heading('#0', text='Crossing', anchor=tk.CENTER)
        self.cross_table.heading('#1', text='Position', anchor=tk.CENTER)
        self.cross_table.column('#0', stretch=tk.YES, width=60)
        self.cross_table.column('#1', stretch=tk.YES, width=60)
        self.cross_table.grid(row=30, column=5, padx=5, pady=5, columnspan=3, sticky='nsew')
        self.treeview = self.cross_table

        self.signal_table = ttk.Treeview(master, height=7, columns=('Signal', 'Color'), selectmode='extended')
        self.signal_table.heading('#0', text='Signal', anchor=tk.CENTER)
        self.signal_table.heading('#1', text='Color', anchor=tk.CENTER)
        self.signal_table.column('#0', stretch=tk.YES, width=60)
        self.signal_table.column('#1', stretch=tk.YES, width=60)
        self.signal_table.grid(row=30, column=5, padx=5, pady=5, columnspan=3, sticky='nsew')
        self.treeview = self.signal_table

        self.station_table = ttk.Treeview(master, height=7, columns=('Station', 'Passenger Count'),
                                          selectmode='extended')
        self.station_table.heading('#0', text='Station', anchor=tk.CENTER)
        self.station_table.heading('#1', text='Passenger Count', anchor=tk.CENTER)
        self.station_table.column('#0', stretch=tk.YES, width=60)
        self.station_table.column('#1', stretch=tk.YES, width=60)
        self.station_table.grid(row=30, column=5, padx=5, pady=5, columnspan=3, sticky='nsew')
        self.treeview = self.station_table

        self.pf_button = tk.Button(master, text="Power Failure")
        self.pf_button.grid(row=30, column=10, sticky="nsew")
        self.br_button = tk.Button(master, text="Broken Rail")
        self.br_button.grid(row=31, column=10, sticky="nsew")
        self.tc_button = tk.Button(master, text="TC Failure")
        self.tc_button.grid(row=32, column=10, sticky="nsew")

    @staticmethod
    def greet():
        print("Greetings!")


root = tk.Tk()
root.grid_columnconfigure(0, weight=10)
my_gui = MainWindow(root)
root.mainloop()
