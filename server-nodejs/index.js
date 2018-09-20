
import Net from 'net';

const server = Net.createServer(sock => {
    
    sock.pipe(sock);
    console.log('Client connected');
    sock.on('end', () => console.log('Connection Finished'));
    sock.on('data', data => {
        console.log('status:', sock.write(data));
        console.log('client message:', data.toString());
    });
})

server.listen(3030, () => {
    console.log('Server Listenning on:', server.address());
});