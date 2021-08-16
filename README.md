# DataDog
* How to use the service
   * Run HttpTraffic.java class
## Assumptions Made
* Logs files are sorted and any new log coming in is in the future with respect to time.

## Proposed design and solution implemented so far
* Reader reads the input from the source line by line into the inmemory buffer and inserts into ArrayDeque.
* Size of the arrayDeque is always maintained to have logs only in the time range of last 2 mins. This way we do not have to store all the logs in the queue which might not be meemory efficient 
or not even fit in the memory.
* Processor gets the first element from the queue and checks with lastest log timestamp inorder to remove elements from the queue which are older than 2 minutes.
* Processor also checks if the total number of website hits is beyond a maximum threshold and also checks if the number of website hits has fallen below a minimum threshold and alerts the system accordingly.

## Improvements to be made/TODO's
* As there could be millions of hits on the websites, going with the arrayDeque datastructure might not be memory efficient.
* probabilistic data structures like count-min sketch can be used. 


## Technology Stack
* Java 1.8 


## Non functional requirements
* Scalability
	* Currently this service is running on a single server, and can take upto 200 concurrent requests. However, in order to handle billion’s of requests, we need to increase servers.
   ```
   Lets say each request takes 2sec which is the latency
   (No of concurrent connections per server * No of servers) = Total no of requests per sec * Latency
   No of servers = (Total no of requests per sec * Latency)/No of concurrent connections per server	
   ```
* Load testing
  * Service should continue to have the same latency even if the load on the application increases indefinitely.
  * Used a postman collection runner to run multiple requests at the same time without any delay. 
