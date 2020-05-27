# FTC dashboard
Dashboard is a useful tool for FTC teams, helping them to review and debugg code better.

made by   
1. adam rubinfeld (orbit 14872 alumni)  
2. team apollo 9662 (Israel)
3. Ofir Siboni (FRC Steampunk 1577)



## Installation
- make sure you have updated Java installed on your mechine.
- Download [FTC app](https://github.com/adamrubinfeld/FTC-app)  
- copy the file from ftc app and paste in team code  
- Download and unzip [FTC dashboard](https://github.com/adamrubinfeld/Dashboard) 
- Install [Intellij](https://www.jetbrains.com/idea/download/#section=windows) On your computer. 


# Usage
- Before making any changes, make sure to backup your Robot's code, If you have Github repository - It might be a good time to [commit & push](https://readwrite.com/2013/10/02/github-for-beginners-part-2/)
- Through your computer, connect to Wifi Direct hosted by your robot controller.  
- Open FTC Dashboard on Intellij and run it.
- Copy the IP address on the console  
![image1](/images/image1.png)  

- Add this code in your Teleop/autonomus
```java
Threat dashboard = new mainThreat("<ip>", <team number>);

@Override
public void init(){
    dashboard.start();
}

@Override
public void stop(){
    client.close();
}
```
- Now, each time you want to add information to your dashboard, you should use Dashboard functions,
  You will note that information will be displayed on the opened window while running.
  go to [mainThreat](https://github.com/adamrubinfeld/FTC-app/blob/master/dashboardUtill/mainThreat.java) and change what you want to see in each graph
 example:
```java
sendDrive(<lf>,<lb>,<rf>,<rb>)
sendVertical(<speed>,<current pos>,<wanted pos>);
sendHorizontal(<speed>,<current pos>,<wanted pos>);
markEndOfUptade();
```
  

# Contact me
Instagram:  
[adamrubinfeld19](https://instagram.com/adamrubinfeld19?igshid=1d8lyv6hdvi601)

Gmail:  
[adamrubin01@gmail.com](mailto:adamrubin01@gmail.com)


# License
dashboard: [MIT](https://github.com/adamrubinfeld/Dashboard/blob/master/LICENSE)  
ftc app: [MIT](https://github.com/adamrubinfeld/FTC-app/blob/master/LICENSE)
