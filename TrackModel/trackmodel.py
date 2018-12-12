import tkinter as tk
from tkinter import ttk
from tkinter.scrolledtext import ScrolledText
from tkinter.scrolledtext import Scrollbar
from tkinter.filedialog import askopenfilename
import xml.etree.ElementTree
import os
import csv
from time import sleep
import random
from threading import Thread


# Main Window
class MainWindow(tk.Frame):
    #class variables
    throughput = 0
    temperature = 0
    
    # load file
    def load(self):
        filename = askopenfilename()
        self.throughput = 0
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
            #loop through csv
            for r in csv_reader:
                if i == 0:
                    i += 1
                else:
                    holdr = r
                    ug = ''
                    sw = 0
                    st = ''
                    rc = ''
                    #get station name and insert
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
                            
                        #insert into red table depending on switches
                        #Conversions from metric to imperial done here
                        if sw == 0:
                            self.red_table.insert('', 'end',
                                                  values=(r[1], r[2], str(int(float(r[3])*1.09361)), r[4], str(int(float(r[5])*0.621371)),
                                                          ug, st, '', r[16], r[17], str("{0:0.1f}".format(float(r[8])*3.28084)),
                                                          r[11],
                                                          r[10], 'false', 'off', 'unoccupied', 'working',
                                                          'working', 'working'))
                        else:
                            self.red_table.insert('', 'end',
                                                  values=(r[1], r[2], str(int(float(r[3])*1.09361)), r[4], str(int(float(r[5])*0.621371)), ug, st, sw, r[16], r[17],
                                                          str("{0:0.1f}".format(float(r[8])*3.28084)), r[11],
                                                          r[10], 'false', 'off', 'unoccupied', 'working',
                                                          'working', 'working'))
                    #adds green rails to project
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
                        
                        #insert into green table. converts metric to imperial
                        if sw == 0:
                            self.green_table.insert('', 'end',
                                                    values=(r[1], r[2], str(int(float(r[3])*1.09361)), r[4], str(int(float(r[5])*0.621371)), ug, st, '',
                                                            r[16], r[17], str("{0:0.1f}".format(float(r[8])*3.28084)),
                                                            r[11],
                                                            r[10], 'false', 'off', 'unoccupied', 'working',
                                                            'working', 'working'))
                        else:
                            self.green_table.insert('', 'end',
                                                    values=(
                                                    r[1], r[2], str(int(float(r[3])*1.09361)), r[4], str(int(float(r[5])*0.621371)),
                                                    ug, st, sw, r[16], r[17], str("{0:0.1f}".format(float(r[8])*3.28084)), r[11],
                                                    r[10], 'false', 'off', 'unoccupied', 'working',
                                                    'working', 'working'))

                    #load into station table
                    if st != '':
                        if st not in stations:
                            stations.append(st)
                            self.station_table.insert('', 'end', values=(st, 0))
                            
                    #load into switch table
                    if sw != 0:
                        self.switch_table.insert('', 'end',
                                                 values=(sw, 'normal', holdr[12], holdr[14], holdr[13], holdr[15]))
                        
                    #load into cross table
                    if r[16] != '':
                        self.cross_table.insert('', 'end', values=(r[16], 'up'))

                    #load into signal table
                    if r[17] != '':
                        self.signal_table.insert('', 'end', values=(r[17], 'red', r[18]))


        #start thread to keep it running
        Thread(target=self.data_update).start()


    # Power Failure
    def power_failure(self):
        #RED TABLE power failure function. Called from UI
        if self.red_table.set(self.red_table.focus(), column='Power Failure') == 'FAILED':
            self.red_table.set(self.red_table.focus(), column='Power Failure', value='working')
            if (self.red_table.item(self.red_table.focus(), 'values')[18] != 'BROKEN') and (self.red_table.item(self.red_table.focus(), 'values')[17] != 'FAILED'):
                self.red_table.set(self.red_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.red_table.set(self.red_table.focus(), column='Power Failure', value='FAILED')
            self.red_table.set(self.red_table.focus(), column='Occupation', value='occupied')

        #GREEN Table power failure function. called from UI
        if self.green_table.set(self.green_table.focus(), column='Power Failure') == 'FAILED':
            self.green_table.set(self.green_table.focus(), column='Power Failure', value='working')
            if (self.green_table.item(self.green_table.focus(), 'values')[18] != 'BROKEN') and (self.green_table.item(self.green_table.focus(), 'values')[17] != 'FAILED'):
                self.green_table.set(self.green_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.green_table.set(self.green_table.focus(), column='Power Failure', value='FAILED')
            self.green_table.set(self.green_table.focus(), column='Occupation', value='occupied')

        self.selection_clear()


    #Brek rail
    def break_rail(self):
        #RED TABLE break rail function. Called from UI
        if self.red_table.set(self.red_table.focus(), column='Broken Rail') == 'BROKEN':
            self.red_table.set(self.red_table.focus(), column='Broken Rail', value='working')
            if (self.red_table.item(self.red_table.focus(), 'values')[16] != 'FAILED') and (self.red_table.item(self.red_table.focus(), 'values')[17] != 'FAILED'):
                self.red_table.set(self.red_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.red_table.set(self.red_table.focus(), column='Broken Rail', value='BROKEN')
            self.red_table.set(self.red_table.focus(), column='Occupation', value='occupied')

        #Green TABLE break rail function. Called from UI
        if self.green_table.set(self.green_table.focus(), column='Broken Rail') == 'BROKEN':
            self.green_table.set(self.green_table.focus(), column='Broken Rail', value='working')
            if (self.green_table.item(self.green_table.focus(), 'values')[16] != 'FAILED') and (self.green_table.item(self.green_table.focus(), 'values')[17] != 'FAILED'):
                self.green_table.set(self.green_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.green_table.set(self.green_table.focus(), column='Broken Rail', value='BROKEN')
            self.green_table.set(self.green_table.focus(), column='Occupation', value='occupied')

        self.selection_clear()


    #Cause tc fail
    def tc_fail(self):
        #RED TABLE track circuit failure function. Called from UI
        if self.red_table.set(self.red_table.focus(), column='TC Failure') == 'FAILED':
            self.red_table.set(self.red_table.focus(), column='TC Failure', value='working')
            if (self.red_table.item(self.red_table.focus(), 'values')[18] != 'BROKEN') and (self.red_table.item(self.red_table.focus(), 'values')[16] != 'FAILED'):
                self.red_table.set(self.red_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.red_table.set(self.red_table.focus(), column='TC Failure', value='FAILED')
            self.red_table.set(self.red_table.focus(), column='Occupation', value='occupied')

        #GREEN TABLE track circuit failure function. Called from UI
        if self.green_table.set(self.green_table.focus(), column='TC Failure') == 'FAILED':
            self.green_table.set(self.green_table.focus(), column='TC Failure', value='working')
            if (self.green_table.item(self.green_table.focus(), 'values')[18] != 'BROKEN') and (self.green_table.item(self.green_table.focus(), 'values')[16] != 'FAILED'):
                self.green_table.set(self.green_table.focus(), column='Occupation', value='unoccupied')
        else:
            self.green_table.set(self.green_table.focus(), column='TC Failure', value='FAILED')
            self.green_table.set(self.green_table.focus(), column='Occupation', value='occupied')

        self.selection_clear()

    #change temperature
    def change_temp(self):
        #Get value from textbox in UI
        num = self.e1.get()
        #Makes sure value is number
        if self.is_number(num):
            #Set global temperature with num
            self.temperature = num
            #UPDATE UI
            self.cur_temp['text'] = (str(self.temperature))
            #Checks to turn off and on heaters
            if int(num) < 32:
                #Turn on heaters for red and green track
                for outs in self.redtree.get_children():
                    self.redtree.set(outs, column='Track Heater', value='on')
                for outs in self.greentree.get_children():
                    self.greentree.set(outs, column='Track Heater', value='on')
            else:
                #Turn off heaters for red and green track
                for outs in self.redtree.get_children():
                    self.redtree.set(outs, column='Track Heater', value='off')
                for outs in self.greentree.get_children():
                    self.greentree.set(outs, column='Track Heater', value='off')
        else:
            #Displays if value entered not a number
            print("Please enter a number")


    #Main Loop updates data and xmls
    def data_update(self):
        #Communicate with track controller
        self.track_controller()
        #Communicate with train model
        self.train_model()
        #Updates people at station
        self.update_stations()
        #Sleep for a quarter of a second to decrease stress on system
        sleep(.25)
        #Calls itself to loop until failure or user exits
        self.data_update()
        

    #Writes XML to Track Controller
    def track_controller(self):
        #Sets track model outputs for track controller to read
        self.write_to_track_controller()
        #takes in values from track controller
        self.read_from_track_controller()
        

    #Talks to train model
    def train_model(self):
        #Checks to see if shared xml file with train model is there
        if os.path.isfile(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+r"\xml\trackmodel_trainmodel.xml"):
            #aquire the file
            xfile = xml.etree.ElementTree.parse(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+r"\xml\trackmodel_trainmodel.xml")
            #Gets root of the xml file
            root = xfile.getroot()
            #Loops through all values of train
            for child in root.findall("Train"):
                #get the id of the train
                train_id = child.get('id')
                #check if track values for train needs to be updated
                if child.get('next') == '1':
                    #Trains on green line
                    if train_id[0]=='G':
                        #get current track num and direction of train
                        direction = child.get('direction')
                        track_num = child.get('trackNumber')
                        #Obtain the new track num and direction
                        val_ret = self.get_next_green_track(track_num, direction)
                        new_track = val_ret[0]
                        direction = val_ret[1]
                        #get the next station, the authority to it, and total authority
                        new_authority = self.get_next_green_auth(track_num, direction)
                        new_station = self.next_green_station(track_num, direction)
                        station_auth = self.green_station_auth(track_num, direction)
                        #Set the new values in the shared xml files 
                        child.set('nextStation', new_station)
                        child.set('stationAuthority', station_auth)
                        child.set('trackNumber', new_track)
                        child.set('authority', new_authority)
                        child.set('direction', direction)
                        #Loop through to update occupancy of tracks
                        for outs in self.greentree.get_children():
                            #unoccupy the old track and if station update passengers at station
                            if self.greentree.item(outs, 'values')[1] == track_num:
                                self.greentree.set(outs, column='Occupation', value='unoccupied')
                                if self.greentree.item(outs, 'values')[6] != "":
                                    self.throughput_calc(self.greentree.item(outs, 'values')[6])
                            #occupy new track and get new track values
                            if self.greentree.item(outs, 'values')[1] == new_track:
                                new_speed = self.greentree.item(outs, 'values')[4]
                                new_ele = self.greentree.item(outs, 'values')[10]
                                new_grade = self.greentree.item(outs, 'values')[3]
                                new_length = self.greentree.item(outs, 'values')[2]
                                self.greentree.set(outs, column='Occupation', value='occupied')
                                break
                        #Set the values for the new track block
                        child.set('speed', new_speed)
                        child.set('elevation', new_ele)
                        child.set('grade', new_grade)
                        child.set('length', new_length)
                        #Set 0 and wait until train model sets it to 1
                        child.set('next', '0')
                        
                    #Trains on red line        
                    else:
                        direction = child.get('direction')
                        track_num = child.get('trackNumber')
                        new_track = self.get_next_red_track(track_num, direction)
                        new_authority = self.get_next_red_auth(track_num, direction)
                        child.set('trackNumber', new_track)
                        for outs in self.redtree.get_children():
                            if self.redtree.item(outs, 'values')[1] == track_num:
                                self.redtree.set(outs, column='Occupation', value='unoccupied')
                                if self.redtree.item(outs, 'values')[6] != "":
                                    new_station = self.next_red_station(track_num, direction)
                                    child.set('next_station', new_station)
                                    self.throughput_calc(self.redtree.item(outs, 'values')[6])
                            if self.redtree.item(outs, 'values')[1] == new_track:
                                new_speed = self.redtree.item(outs, 'values')[4]
                                new_ele = self.redtree.item(outs, 'values')[10]
                                new_grade = self.redtree.item(outs, 'values')[3]
                                new_length = self.redtree.item(outs, 'values')[2]
                                self.redtree.set(outs, column='Occupation', value='occupied')
                                break
                        child.set('speed', new_speed)
                        child.set('elevation', new_ele)
                        child.set('grade', new_grade)
                        child.set('length', new_length)
                        child.set('next', '0')
                        child.set('authority', new_authority)
                        
            #Write out xml file            
            tree = xml.etree.ElementTree.ElementTree(root)
            tree.write(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+r"\xml\trackmodel_trainmodel.xml")


    #get green auth for station
    def green_station_auth(self, tn, d):
        auth = 0
        #Loop until no more authority
        while(1):
            #Get next green track 
            new_val = self.get_next_green_track(tn, d)
            ntn = new_val[0]
            d = new_val[1]
            tn = ntn
            #sum auth until a station is detected. Lets train model know distance to station
            for outs in self.greentree.get_children():
                if self.greentree.item(outs, 'values')[1] == ntn:
                    auth = auth + int(self.greentree.item(outs, 'values')[2])
                    if self.greentree.item(outs, 'values')[6] == "":
                        break
                    else:
                        return str(auth)
                    
                    
    
    #get next green track
    def get_next_green_track(self, tn, d):
        if tn == 'n/a':
            return 'n/a'
        tnn = int(tn)
        retList = [tn, d]
        if tnn == 0:
            return ['62', 'f']
        elif tnn == 100:
            return ['85', 'r']
        elif tnn == 150:
            return ['28', 'r']
        elif tnn == 77 and d == 'r':
            return ['101', 'f']
        elif tnn == 1:
            return ['13', 'f']
        else:
            for outs in self.greentree.get_children():
                if self.greentree.item(outs, 'values')[1] == tn:
                    #case for no switch
                    if self.greentree.item(outs, 'values')[7] == "":
                        if d == 'f':
                            return [str(tnn + 1), d]
                        else:
                            return [str(tnn-1), d]
                    else:
                        switch_num = self.greentree.item(outs, 'values')[7]
                        for outs in self.switchtree.get_children():
                            if self.switchtree.item(outs, 'values')[0] == switch_num:
                                switch_pos = self.switchtree.item(outs, 'values')[1]
                                if switch_pos == "normal":
                                    if d == 'f':
                                        return [str(self.switchtree.item(outs, 'values')[2]), 'f']
                                    else:
                                        return [str(self.switchtree.item(outs, 'values')[3]), 'r']
                                else:
                                    if d == 'f':
                                        return [str(self.switchtree.item(outs, 'values')[4]), 'f']
                                    else:
                                        return [str(self.switchtree.item(outs, 'values')[5]), 'r']
                                

    #get next red track
    def get_next_red_track(self, tn, d):
        tnn = int(tn)
        if tnn == 0:
            return '9'
        else:
            for outs in self.redtree.get_children():
                if self.redtree.item(outs, 'values')[1] == tn:
                    #case for no switch
                    if self.redtree.item(outs, 'values')[7] == "":
                        if d == 'f':
                            return str(tnn + 1)
                        else:
                            return str(tnn-1)
                    else:
                        switch_num = self.redtree.item(outs, 'values')[7]
                        for outs in self.switchtree.get_children():
                            if self.switchtree.item(outs, 'values')[0] == switch_num:
                                switch_pos = self.switchtree.item(outs, 'values')[1]
                                if switch_pos == "normal":
                                    if d == 'f':
                                        return str(self.switchtree.item(outs, 'values')[2])
                                    else:
                                        return str(self.switchtree.item(outs, 'values')[3])
                                else:
                                    if d == 'f':
                                        return str(self.switchtree.item(outs, 'values')[4])
                                    else:
                                        return str(self.switchtree.item(outs, 'values')[5])
                

    #get green auth
    def get_next_green_auth(self, tn, d):
        auth = 0
        while(1):
            new_val = self.get_next_green_track(tn, d)
            ntn = new_val[0]
            d = new_val[1]
            if ntn == 'yard' or ntn == 'n/a':
                break
            tn = ntn
            for outs in self.greentree.get_children():
                if self.greentree.item(outs, 'values')[1] == ntn:
                    auth_added = int(self.greentree.item(outs, 'values')[2])
                    if self.greentree.item(outs, 'values')[9] == "" and self.greentree.item(outs, 'values')[8] == "":
                        auth = auth + int(auth_added)
                    elif self.greentree.item(outs, 'values')[9] != "" and self.greentree.item(outs, 'values')[8] == "":
                        sig_num = self.greentree.item(outs, 'values')[9]
                        for outs in self.signaltree.get_children():
                            if self.signaltree.item(outs, 'values')[0] == sig_num:
                                if self.signaltree.item(outs, 'values')[2] == d.upper():
                                    if self.signaltree.item(outs, 'values')[1] == "red":
                                        return str(auth + int(auth_added))
                                    else:
                                        auth = auth + int(auth_added)
                                        break
                                else:
                                    auth = auth + int(auth_added)
                                    break
                    else:
                        cross_num = self.greentree.item(outs, 'values')[8]
                        for outs in self.crosstree.get_children():
                            if self.crosstree.item(outs, 'values')[0] == cross_num:
                                if self.crosstree.item(outs, 'values')[1] == "down":
                                    return str(auth + int(auth_added))
                                else:
                                    auth = auth + int(auth_added)
                                    break
                        
        return str(auth)


    #get red auth
    def get_next_red_auth(self, tn, d):
        auth = 0
        while(1):
            ntn = self.get_next_red_track(tn, d)
            tn = ntn
            for outs in self.redtree.get_children():
                if self.redtree.item(outs, 'values')[1] == ntn:
                    auth_added = int(self.redtree.item(outs, 'values')[2])
                    if self.redtree.item(outs, 'values')[9] == "":
                        auth = auth + auth_added
                    else:
                        sig_num = self.redtree.item(outs, 'values')[9]
                        for outs in self.signaltree.get_children():
                            if self.signaltree.item(outs, 'values')[0] == sig_num:
                                auth_added = self.redtree.item(outs, 'values')[2]
                                if self.signaltree.item(outs, 'values')[2] == d.upper():
                                    if self.signaltree.item(outs, 'values')[1] == "red":
                                        return str(auth + auth_added)
                                    else:
                                        auth = auth + auth_added
                                        break
                                else:
                                    auth = auth + auth_added
                                    break
            break
                            
        return str(auth)
    

    #def next green station
    def next_green_station(self, tn, d):
        while(1):
            new_val = self.get_next_green_track(tn, d)
            ntn = new_val[0]
            if ntn == 'n/a' or ntn == 'yard':
                break
            d = new_val[1]
            tn = ntn
            for outs in self.greentree.get_children():
                if self.greentree.item(outs, 'values')[1] == ntn:
                    if self.greentree.item(outs, 'values')[6] == "":
                        break
                    else:
                        return self.greentree.item(outs, 'values')[6]
        

    #def next red station
    def next_red_station(self, tn, d):
        while(1):
            ntn = self.get_next_red_track(tn, d)
            tn = ntn
            for outs in self.redtree.get_children():
                if self.redtree.item(outs, 'values')[1] == ntn:
                    if self.redtree.item(outs, 'values')[6] == "":
                        break
                    else:
                        return self.redtree.item(outs, 'values')[6]


    #def throughput
    def throughput_calc(self, st):
        for outs in self.stationtree.get_children():
            if self.stationtree.item(outs, 'values')[0] == st:
                output = self.stationtree.item(outs, 'values')[1]
                self.stationtree.set(outs, column="Passenger Count", value=0)
                self.throughput = self.throughput + int(output)
                
                
    #update stations
    def update_stations(self):
        for outs in self.stationtree.get_children():
            y = self.stationtree.item(outs, 'values')[1]
            if int(y) < 30:
                x = random.randint(1, 2)
                y = int(y) + x
                self.stationtree.set(outs, column="Passenger Count", value=y)


    #Determines if number
    def is_number(self, s):
        try:
            float(s)
            return True
        except ValueError:
            return False

    
    #reads from track controller and sets switches and signals
    def read_from_track_controller(self):
        #Checks to see file is present
        if os.path.isfile(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+"\\xml\TrackControllerOutputs.xml"):
            #Load file and get root
            xfile = xml.etree.ElementTree.parse(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+"\\xml\TrackControllerOutputs.xml")
            root = xfile.getroot()
            #loop through all <bit> </bit> in xml file
            for child in root.findall("bit"):
                #get name of each xml object and deconstruct it to find track type and location
                read = child.get('name')
                position = int(child.text)
                line_select = read[0]
                
                #selects line to alter
                if line_select == 'R':
                    #red line
                    #selects track block and type                  
                    if self.is_number(read[2]):
                        track_block = read[1:3]
                        #Update signals
                        if read[3] == "G":
                            for outs in self.redtree.get_children():
                                if self.redtree.item(outs, 'values')[1] == track_block:
                                    sig_num = self.redtree.item(outs, 'values')[9]
                                    break
                            for outs in self.signaltree.get_children():
                                if self.signaltree.item(outs, 'values')[0] == sig_num:
                                    if position == 1:
                                        self.signaltree.set(outs, column='Color', value='green')
                                    else:
                                        self.signaltree.set(outs, column='Color', value='red')
                                    break
                            
                        elif read[3] == "S":
                            #no double digit switches on red line
                            print("add Red Switch 2 number code")
                            
                        #Update r-xings   
                        else:
                            for outs in self.redtree.get_children():
                                if self.redtree.item(outs, 'values')[1] == track_block:
                                    cross_num = self.redtree.item(outs, 'values')[8]
                                    break
                            for outs in self.crosstree.get_children():
                                if self.crosstree.item(outs, 'values')[0] == cross_num:
                                    if position == 1:
                                        self.crosstree.set(outs, column='Position', value='up')
                                    else:
                                        self.crosstree.set(outs, column='Position', value='down')
                                    break
                    else:
                        track_block = read[1]
                        #more signals on red line
                        if read[2] == "G":
                            for outs in self.redtree.get_children():
                                if self.redtree.item(outs, 'values')[1] == track_block:
                                    sig_num = self.redtree.item(outs, 'values')[9]
                                    break
                            for outs in self.signaltree.get_children():
                                if self.signaltree.item(outs, 'values')[0] == sig_num:
                                    if position == 1:
                                        self.signaltree.set(outs, column='Color', value='green')
                                    else:
                                        self.signaltree.set(outs, column='Color', value='red')
                                    break
                        #update switch on red line        
                        elif read[2] == "S":
                            tb6 = int(track_block) + 6
                            cmp_tb = str(tb6)
                            for outs in self.switchtree.get_children():
                                if self.switchtree.item(outs, 'values')[0] == cmp_tb:
                                    if position == 0:
                                        self.switchtree.set(outs, column='Position', value='reverse')
                                    else:
                                        self.switchtree.set(outs, column='Position', value='normal')
                                    break
                        #Update r-xings                                      
                        else:
                            for outs in self.redtree.get_children():
                                if self.redtree.item(outs, 'values')[1] == track_block:
                                    cross_num = self.redtree.item(outs, 'values')[8]
                                    break
                            for outs in self.crosstree.get_children():
                                if self.crosstree.item(outs, 'values')[0] == cross_num:
                                    if position == 1:
                                        self.crosstree.set(outs, column='Position', value='up')
                                    else:
                                        self.crosstree.set(outs, column='Position', value='down')
                                    break

                else:
                    #Green line
                    if self.is_number(read[3]):
                        track_block = read[1:4]
                        #Signals in triple digit track blocks
                        if read[4] == "G":
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    sig_num = self.greentree.item(outs, 'values')[9]
                                    break
                            for outs in self.signaltree.get_children():
                                if self.signaltree.item(outs, 'values')[0] == sig_num:
                                    if position == 1:
                                        self.signaltree.set(outs, column='Color', value='green')
                                    else:
                                        self.signaltree.set(outs, column='Color', value='red')
                                    break
                        elif read[4] == "S":
                            print("add Green Switch 3 number code")

                        #Crossings in triple digit track blocks
                        else:
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    cross_num = self.greentree.item(outs, 'values')[8]
                                    break
                            for outs in self.crosstree.get_children():
                                if self.crosstree.item(outs, 'values')[0] == cross_num:
                                    if position == 1:
                                        self.crosstree.set(outs, column='Position', value='up')
                                    else:
                                        self.crosstree.set(outs, column='Position', value='down')
                                    break

                    elif self.is_number(read[2]):
                        track_block = read[1:3]
                        #Signals in double digit track blocks
                        if read[3] == "G":
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    sig_num = self.greentree.item(outs, 'values')[9]
                                    break
                            for outs in self.signaltree.get_children():
                                if self.signaltree.item(outs, 'values')[0] == sig_num:
                                    if position == 1:
                                        self.signaltree.set(outs, column='Color', value='green')
                                    else:
                                        self.signaltree.set(outs, column='Color', value='red')
                        elif read[3] == "S":
                            print("add Green Switch 2 number code")

                        #Crossings in double digit track blocks
                        else:
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    cross_num = self.greentree.item(outs, 'values')[8]
                                    break
                            for outs in self.crosstree.get_children():
                                if self.crosstree.item(outs, 'values')[0] == cross_num:
                                    if position == 1:
                                        self.crosstree.set(outs, column='Position', value='up')
                                    else:
                                        self.crosstree.set(outs, column='Position', value='down')
                                    break
                    else:
                        track_block = read[1]
                        #Signals in single digit track blocks
                        if read[2] == "G":
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    sig_num = self.greentree.item(outs, 'values')[9]
                                    break
                            for outs in self.signaltree.get_children():
                                if self.signaltree.item(outs, 'values')[0] == sig_num:
                                    if position == 1:
                                        self.signaltree.set(outs, column='Color', value='green')
                                    else:
                                        self.signaltree.set(outs, column='Color', value='red')

                        #Switches in green line
                        elif read[2] == "S":
                            tb6 = int(track_block)
                            cmp_tb = str(tb6)
                            for outs in self.switchtree.get_children():
                                if self.switchtree.item(outs, 'values')[0] == cmp_tb:
                                    if position == 0:
                                        self.switchtree.set(outs, column='Position', value='reverse')
                                    else:
                                        self.switchtree.set(outs, column='Position', value='normal')
                                    break
                        #Crossings in green line
                        else:
                            for outs in self.greentree.get_children():
                                if self.greentree.item(outs, 'values')[1] == track_block:
                                    cross_num = self.greentree.item(outs, 'values')[8]
                                    break
                            for outs in self.crosstree.get_children():
                                if self.crosstree.item(outs, 'values')[0] == cross_num:
                                    if position == 1:
                                        self.crosstree.set(outs, column='Position', value='up')
                                    else:
                                        self.crosstree.set(outs, column='Position', value='down')
                                    break
        else:
            #prints if track controller file is not created
            print("No Track Controller Outputs")

                    
    #writes occupancies to track controller
    def write_to_track_controller(self):
        #Check to see if file present
        if os.path.isfile(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+"\\xml\TrackModelOutputs.xml"):
            #Obtain file and root
            xfile = xml.etree.ElementTree.parse(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+"\\xml\TrackModelOutputs.xml")
            root = xfile.getroot()
            #Loop through red table and update occupancy in xml file. '0' is occupied '1' unoccupied
            for outs in self.redtree.get_children():
                i = 1
                name_build = "R"
                track_number = self.redtree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                if self.redtree.item(outs, 'values')[15] == "occupied":
                    find_string = './/bit[@name='+"\""+name_build+"\""+']'
                    root.find(find_string).text = '0'
                else:
                    find_string = './/bit[@name='+"\""+name_build+"\""+']'
                    root.find(find_string).text = '1'
                i += 1
            #Loop through green table and update occupancy in xml file. '0' is occupied '1' unoccupied
            for outs in self.greentree.get_children():
                i = 1
                name_build = "G"
                track_number = self.greentree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                if self.greentree.item(outs, 'values')[15] == "occupied":
                    find_string = './/bit[@name='+"\""+name_build+"\""+']'
                    root.find(find_string).text = '0'
                else:
                    find_string = './/bit[@name='+"\""+name_build+"\""+']'
                    root.find(find_string).text = '1'
                i += 1

            #Update throughput
            root.find('throughput').text = str(self.throughput)
            
        #file not present, create it    
        else:
            root = xml.etree.ElementTree.Element("bits")
            #Loop through red table and create occupancy in xml file. '0' is occupied '1' unoccupied
            for outs in self.redtree.get_children():
                i = 1
                name_build = "R"
                track_number = self.redtree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                if self.redtree.item(outs, 'values')[15] == "occupied":
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                 name=name_build).text = '0'
                else:
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                     name=name_build).text = '1'
                i += 1
            #Loop through green table and create occupancy in xml file. '0' is occupied '1' unoccupied
            for outs in self.greentree.get_children():
                i = 1
                name_build = "G"
                track_number = self.greentree.item(outs, 'values')[1]
                name_build = name_build + track_number + "T"
                if self.greentree.item(outs, 'values')[15] == "occupied":
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                 name=name_build).text = '0'
                else:
                    xml.etree.ElementTree.SubElement(root, "bit",
                                                     name=name_build).text = '1'
                i += 1
            #Create throughput
            xml.etree.ElementTree.SubElement(root, "throughput",
                                                     name='throughput').text = str(self.throughput)

        #Write out xml file
        tree = xml.etree.ElementTree.ElementTree(root)
        tree.write(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))+"\\xml\TrackModelOutputs.xml")
        

    #Create window and initialize UI
    def __init__(self, master, *args, **kwargs):
        #initialize menu, title, and settings
        self.master = master
        self.throughput = 0
        self.temperature = 70
        master.title("Track Model UI")
        tk.Frame.__init__(self, *args, **kwargs)
        menubar = tk.Menu(master)
        filemenu = tk.Menu(menubar, tearoff=0)
        filemenu.add_command(label="Load CSV", command=self.load)
        menubar.add_cascade(label="File", menu=filemenu)
        root.config(menu=menubar)

        self.label = tk.Label(master, text='Red Line')
        self.label.grid(row=0, column=0, sticky='nsw')

        #red table
        self.red_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                 'Underground', 'Station', 'Switch', 'railway x-ing', 'signal',
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
        self.red_table.heading('#10', text='Signal', anchor=tk.CENTER)
        self.red_table.heading('#11', text='Elevation', anchor=tk.CENTER)
        self.red_table.heading('#12', text='Direction', anchor=tk.CENTER)
        self.red_table.heading('#13', text='Bidirectional', anchor=tk.CENTER)
        self.red_table.heading('#14', text='Track Block', anchor=tk.CENTER)
        self.red_table.heading('#15', text='Track Heater', anchor=tk.CENTER)
        self.red_table.heading('#16', text='Occupation', anchor=tk.CENTER)
        self.red_table.heading('#17', text='Power Failure', anchor=tk.CENTER)
        self.red_table.heading('#18', text='TC Failure', anchor=tk.CENTER)
        self.red_table.heading('#19', text='Broken Rail', anchor=tk.CENTER)
        self.red_table.column('#1', stretch=tk.YES, width=50)
        self.red_table.column('#2', stretch=tk.YES, width=40)
        self.red_table.column('#3', stretch=tk.YES, width=50)
        self.red_table.column('#4', stretch=tk.YES, width=50)
        self.red_table.column('#5', stretch=tk.YES, width=50)
        self.red_table.column('#6', stretch=tk.YES, width=80)
        self.red_table.column('#7', stretch=tk.YES, width=140)
        self.red_table.column('#8', stretch=tk.YES, width=50)
        self.red_table.column('#9', stretch=tk.YES, width=90)
        self.red_table.column('#10', stretch=tk.YES, width=40)
        self.red_table.column('#11', stretch=tk.YES, width=60)
        self.red_table.column('#12', stretch=tk.YES, width=60)
        self.red_table.column('#13', stretch=tk.YES, width=90)
        self.red_table.column('#14', stretch=tk.YES, width=90)
        self.red_table.column('#15', stretch=tk.YES, width=80)
        self.red_table.column('#16', stretch=tk.YES, width=90)
        self.red_table.column('#17', stretch=tk.YES, width=80)
        self.red_table.column('#18', stretch=tk.YES, width=70)
        self.red_table.column('#19', stretch=tk.YES, width=70)
        self.red_table['show'] = 'headings'
        self.red_table.grid(row=1, column=0, columnspan=20, padx=5, pady=5, sticky='nsew')
        self.redtree = self.red_table

        self.glabel = tk.Label(master, text='Green Line')
        self.glabel.grid(row=2, column=0, columnspan=1, sticky='sw')

        #green table
        self.green_table = ttk.Treeview(master, height=7, columns=('Section', 'Block', 'Length', 'Grade', 'Speed',
                                                                   'Underground', 'Station', 'Switch', 'railway x-ing', 'signal',
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
        self.green_table.heading('#10', text='Signal', anchor=tk.CENTER)
        self.green_table.heading('#11', text='Elevation', anchor=tk.CENTER)
        self.green_table.heading('#12', text='Direction', anchor=tk.CENTER)
        self.green_table.heading('#13', text='Bidirectional', anchor=tk.CENTER)
        self.green_table.heading('#14', text='Track Block', anchor=tk.CENTER)
        self.green_table.heading('#15', text='Track Heater', anchor=tk.CENTER)
        self.green_table.heading('#16', text='Occupation', anchor=tk.CENTER)
        self.green_table.heading('#17', text='Power Failure', anchor=tk.CENTER)
        self.green_table.heading('#18', text='TC Failure', anchor=tk.CENTER)
        self.green_table.heading('#19', text='Broken Rail', anchor=tk.CENTER)
        self.green_table.column('#1', stretch=tk.YES, width=50)
        self.green_table.column('#2', stretch=tk.YES, width=40)
        self.green_table.column('#3', stretch=tk.YES, width=50)
        self.green_table.column('#4', stretch=tk.YES, width=50)
        self.green_table.column('#5', stretch=tk.YES, width=50)
        self.green_table.column('#6', stretch=tk.YES, width=80)
        self.green_table.column('#7', stretch=tk.YES, width=140)
        self.green_table.column('#8', stretch=tk.YES, width=50)
        self.green_table.column('#9', stretch=tk.YES, width=90)
        self.green_table.column('#10', stretch=tk.YES, width=50)
        self.green_table.column('#11', stretch=tk.YES, width=60)
        self.green_table.column('#12', stretch=tk.YES, width=60)
        self.green_table.column('#13', stretch=tk.YES, width=90)
        self.green_table.column('#14', stretch=tk.YES, width=90)
        self.green_table.column('#15', stretch=tk.YES, width=80)
        self.green_table.column('#16', stretch=tk.YES, width=90)
        self.green_table.column('#17', stretch=tk.YES, width=80)
        self.green_table.column('#18', stretch=tk.YES, width=70)
        self.green_table.column('#19', stretch=tk.YES, width=70)
        self.green_table['show'] = 'headings'
        self.green_table.grid(row=3, column=0, columnspan=20, padx=5, pady=5)
        self.greentree = self.green_table

        self.slabel = tk.Label(master, text='Switches, Crossing, Signals, and Murphy')
        self.slabel.grid(row=4, column=0, columnspan=1, sticky='sw')

        #switch table
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

        #cross table
        self.cross_table = ttk.Treeview(master, height=7, columns=('Crossing', 'Position'), selectmode='extended')
        self.cross_table.heading('#1', text='Crossing', anchor=tk.CENTER)
        self.cross_table.heading('#2', text='Position', anchor=tk.CENTER)
        self.cross_table.column('#1', stretch=tk.YES, width=60)
        self.cross_table.column('#2', stretch=tk.YES, width=60)
        self.cross_table['show'] = 'headings'
        self.cross_table.columnconfigure(1, weight=1)
        self.cross_table.grid(row=5, column=13, rowspan=6, padx=5, pady=5, sticky='nsew')
        self.crosstree = self.cross_table

        #signal table
        self.signal_table = ttk.Treeview(master, height=7, columns=('Signal', 'Color', 'Direction'), selectmode='extended')
        self.signal_table.heading('#1', text='Signal', anchor=tk.CENTER)
        self.signal_table.heading('#2', text='Color', anchor=tk.CENTER)
        self.signal_table.heading('#3', text='Direction', anchor=tk.CENTER)
        self.signal_table.column('#1', stretch=tk.YES, width=60)
        self.signal_table.column('#2', stretch=tk.YES, width=60)
        self.signal_table.column('#3', stretch=tk.YES, width=60)
        self.signal_table['show'] = 'headings'
        self.signal_table.grid(row=5, column=15, rowspan=6, padx=5, pady=5, sticky='nsew')
        self.signaltree = self.signal_table

        #station table
        self.station_table = ttk.Treeview(master, height=7, columns=('Station', 'Passenger Count'),
                                          selectmode='extended')
        self.station_table.heading('#1', text='Station', anchor=tk.CENTER)
        self.station_table.heading('#2', text='Passenger Count', anchor=tk.CENTER)
        self.station_table.column('#1', stretch=tk.YES, width=140)
        self.station_table.column('#2', stretch=tk.YES, width=100)
        self.station_table['show'] = 'headings'
        self.station_table.grid(row=5, rowspan=6, column=17, padx=5, pady=5, sticky='nsew')
        self.stationtree = self.station_table

        #murphy buttons
        self.pf_button = tk.Button(master, text="Power Failure", command=self.power_failure)
        self.pf_button.grid(row=5, padx=5, pady=5, column=18, sticky="nsew")
        self.tc_button = tk.Button(master, text="TC Failure", command=self.tc_fail)
        self.tc_button.grid(row=6, padx=5, pady=5, column=18, sticky="nsew")
        self.br_button = tk.Button(master, text="Broken Rail", command=self.break_rail)
        self.br_button.grid(row=7, padx=5, pady=5, column=18, sticky="nsew")

        #Temperature UI
        self.temp_label = tk.Label(master, text="TEMP(F)")
        self.temp_label.grid(row=8, padx=5, pady=5, column=18, sticky="nsw")
        self.e1 = tk.Entry(master, width='10')
        self.e1.grid(row=9, padx=5, pady=5, column=18, sticky="nsew")
        self.temp_button = tk.Button(master, text="Set", command=self.change_temp)
        self.temp_button.grid(row=9, padx=0, pady=5, column=19, sticky="nsew")
        self.cur_temp = tk.Label(master, text=self.temperature)
        self.cur_temp.grid(row=8, padx=5, pady=5, column=18, sticky="nse")

        #Row and Column configures
        self.columnconfigure(2, weight=1)  # column with treeview
        self.rowconfigure(2, weight=1)  # row with treeview
        self.config()


    @staticmethod
    def greet():
        print("Greetings!")

#Tkinter stuff for UI
root = tk.Tk()
root.config()
my_gui = MainWindow(root)
root.mainloop()
