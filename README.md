# Data Set In GridDB Realizes Data Visualization On WEB

This project was built with ECharts, SpringBoot, WebSocket & GridDB. 

After the project runs, the screenshot animation effect is as follows

![screenshot](screenshot/DataVisualization.gif#pic_center)

## Technology Stack
Operating System: Ubuntu-18.04.6\
Front-end: HTML, JavaScript, ECharts 5.4.0, WebSocket\
Back-end: Java 11.0.2, Spring Boot, WebSocket, Maven\
development tool: IntelliJ IDEA 2019\
Database: GridDB 5.1.0

This project is a simple and complete WEB microservice system. The project simulates the data transmission of the Temperature Gauge in the Internet of Things scenario, and  visualizes the changes of the data set in real time on the web. After opening the web page, the web page does not need to be refreshed, and the data changes can be displayed directly on the web page.
  
The data set of Temperature Gauge is stored in GridDB.

The front-end uses ECharts to display the Gauge, and uses SockJs and Stomp to communicate with the back-end through WebSocket. The back-end uses SpringBoot to implement the server end of WebSocket, extract data from GridDB, and transfer the data to the front-end.
