//For run this server type in the terminal
//npm start
import Net, { Socket } from 'net';

var sockets = [];
const server = Net.createServer();
server.listen(3030, () => {
    console.log('Server Listenning on:', server.address());
});

server.on('connection', sock => {
    console.log('Client connected:', sock.address());
    sockets.push(sock);

    sock.on('data', data => {
        console.log('client message:', data.toString());
        if(sockets.length > 0) {
            for(let item of sockets) {
                if(item !== sock) {
                    item.write(data.toString());
                }
            }
        }
    });

    sock.on('end', () => console.log('Connection Finished'));
})