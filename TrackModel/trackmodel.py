import tkinter as tk
from tkinter import ttk
from tkinter.scrolledtext import ScrolledText
from tkinter.scrolledtext import Scrollbar
import xml.etree.ElementTree
import os


class MainWindow(tk.Frame):
    def __init__(self, master, *args, **kwargs):
        self.master = master
        master.title("Track Model")
        tk.Frame.__init__(self, *args, **kwargs)

        pad = 3
        self._geom = '200x200+0+0'
        master.geometry("{0}x{1}+0+0".format(
            master.winfo_screenwidth() - pad, master.winfo_screenheight() - pad))
        master.bind('<Escape>', self.toggle_geom)

        self.label = tk.Label(master, text='Red Line')
        self.label.grid(row=1, column=1, columnspan=1, sticky='nsew')

        self.red_table = ttk.Treeview(master, height=5, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
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
        self.red_table.grid(row=2, sticky='nsew')
        self.treeview = self.red_table

        self.label = tk.Label(master, text='Red Line')
        self.label.grid(row=1, column=1, columnspan=1, sticky='nsew')

    @staticmethod
    def greet():
        print("Greetings!")

    def toggle_geom(self, event):
        geom = self.master.winfo_geometry()
        print(geom, self._geom)
        self.master.geometry(self._geom)
        self._geom = geom


root = tk.Tk()
my_gui = MainWindow(root)
root.mainloop()
