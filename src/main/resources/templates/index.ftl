<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Spring Boot+WebSocket+广播式</title>

</head>
<body onload="disconnect()">
<noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="connect2" onclick="connect2();">aaa</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div id="conversationDiv">
        <label>输入你的名字</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
    </div>
</div>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/sockjs.min.js" type="text/javascript"></script>
<script src="js/stomp.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }

    function connect() {
        var socket = new SockJS('/endpointWisely'); //链接SockJS 的endpoint 名称为"/endpointWisely"
        stompClient = Stomp.over(socket);//使用stomp子协议的WebSocket 客户端
        stompClient.connect({}, function(frame) {//链接Web Socket的服务端。
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/getResponse', function(respnose){ //订阅/topic/getResponse 目标发送的消息。这个是在控制器的@SendTo中定义的。
                console.log("》》》》》》》》》》》》》》》》》》"+respnose)
                showResponse(respnose);
            });
        });
    }
    function connect2() {
        $.get("/te").done(function (resp) {
            console.log("--------------------"+resp)
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        var name = $('#name').val();
        //通过stompClient.send 向/welcome 目标 发送消息,这个是在控制器的@messageMapping 中定义的。
        stompClient.send("/welcome", {}, JSON.stringify({ 'msg': name }));
    }

    function showResponse(message) {
        var response = $("#response");
        response.html(message);
    }
</script>
</body>
</html>