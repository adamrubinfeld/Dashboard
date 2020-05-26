# FTC dashboard
was created to help teams find software problems as well as  
solve them, and to present data in a way that is more convenient to read.


## Installation
Download [FTC app]()  
copy the file from ftc app and paste in team code  
Download [FTC dashboard](https://github.com/adamrubinfeld/Dashboard)  
Download [Intellij](https://www.jetbrains.com/idea/download/#section=windows)  
Open the FTC dashboard on Intellij 

# Usage
Through a computer, connect to direct WiFi through robot control  
Open the FTC dashboard  
Copy the IP address on the console  
![image1](/images/image1.png)  
Paste in your Teleop/autonomus
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
Enter in main Threat  
Enter the data you want to have in each graph
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
[adamrubin01@gmail.com](https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox?compose=GTvVlcSMVxqnqjkDBnBKXKqnjvjxkZFlxTglZDNmGXwhhQsrRQdMvmctGDhgBXLWnncTQgQGKHtdM)


# License
dashboard: [MIT](https://github.com/adamrubinfeld/Dashboard/blob/master/LICENSE)  
ftc app: [MIT]()