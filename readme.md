[Sunil's World Peace] 

My app links maps and disaster bulletins / refugee information
it does this, but i wanted it to do way more 

Main Activity: Google Maps Activity 
OnMapClick --> 
I use the GeoCoder to retrieve the name of the country you clicked on, to pass it as a query parameter to the UNHCR and ReliefWeb APIS. 
There were some annoyances 
Both APIS used different naming conventions and country codes 

Before any onMapClicking(), a get request is made at MapsActivity.onCreate() to store a key-value pair form the UN's site to create a hashmap for subsequent queries to UNHCR

you will see in the API calls that I wrote a few switch statements to handle incongruencies between the APIs and the Geocoder's naming conventions. 

1: GET Request to ReliefWeb API for 15 bulletins (filtered request) 
these bulletins are stored in a singleton in the Callback's onRespose(), 


2: GET Request to United Nations' UNHCR endpoint for "persons of conern," IE individuals originally from country X but are refugees/have sought asylum in countires A B C D ... Z
   This data is persisted with Realm 
   I am very bad at Realm . 
   If you are interested, you can see the onclick saves the relevant queries to a realm database, but I am not yet competent at queryig my realm writes 
   In a spat of delusional ambition, I thought I could use a charting API to show a line graph plotting number of refugees against haven countires. 
   I got the data and stored it, but didn't get further than that...  

3: Back to ReliefWeb (non-persisted) API call: I put the bulletins in card recyclerviews. 
once again, i thought it would be really "neat" to have unique icons pertaining to specific disasters and persons of concern 
Since I have not enrolled in UXDI yet, I just used different color vector circles. I know they aren't helpful in their current state. 
I learned the hardway that my icons are doubled (tripled n-led) if i scroll down, and the recyclerview "recycles" an element, probably running onbind again. I probably should clear any view that gets recycled... version 2(000 :)) 

4: More about the cardview: I originally intended for the cardview to expand to match parent with the body text of the bulletin-in-question. This would require a subsequent GET request, so ideally the animation would buy the app time to get the data before referring to a null.. this didn't happen, so i gave you a webview with some json. sorry. 

World Peace is not easy. But I'm currently rewriting it... I have not given up on world peace. 

Thank you for a very informative three months of instruction, and I wish you all the best in all future endeavors. 

Sincerely, 

Sunil 