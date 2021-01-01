TCP/IP
--

This is an implementation based on TCP/IP protocol. Starting with a simple text based service connected on sockets.

The server setup an active connection listening to the input stream of client and replies with appropriate text from its side.

The client actively send lines from its console. A token of "STOP" will close the socket and streams altogether.

##### How to run?
1. Run Server
   
   a. Send messages from console
   
   b. Send "STOP" to destroy connection.
2. Run Client
    
    a. Likewise. 

##### TODO:
1. Add names to clients.
2. Allow multiple clients to connect.
3. Allow clients to access resources from other clients (files, media, audio)