# FTC dashboard
Dashboard is a useful tool for FTC teams, helping them to review and debugg code better.

made by   
1. adam rubinfeld (orbit 14872 alumni)  
2. team apollo 9662 (Israel)


## Installation
- Download [FTC app](https://github.com/adamrubinfeld/FTC-app)  
- copy the file from ftc app and paste in team code  
- Download and unzip [FTC dashboard](https://github.com/adamrubinfeld/Dashboard) 
- Install [Intellij](https://www.jetbrains.com/idea/download/#section=windows) On your mechine. 


# Usage
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
