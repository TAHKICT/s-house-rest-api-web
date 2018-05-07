class Model {

  constructor(settings) {
    this.settings = settings;
    this.socket = null;
    this.stompClient = null;
      this.container = document.querySelector('#mainContainer');
  }

  getRenderData() {
   return new Promise((resolve, reject) => {

      fetch(this.settings.renderDataUrl)
      .then(res => res.json())
      .then(res => { resolve(res) });

  })
  }

  initWebSoc(onopen, onclose, onmessage, onerror) {
    if (this.settings.webSocDataUrl == '') { console.log('Не установлен адресс для вебсокетов'); return; };
          this.socket = new SockJS(this.settings.webSocDataUrl);
          this.stompClient = Stomp.over(this.socket);
          console.log('this.stompClient.url: ' + this.stompClient.url);
          this.stompClient.connect({}, (frame) => {
              console.log('Connected: ' + frame);
              this.stompClient.subscribe('/to-user/messages', (webSocketMessage) => {
                  this.handleWebSocketMessage(JSON.parse(webSocketMessage.body).nodeId, JSON.parse(webSocketMessage.body).value);
              });
          });


        // this.socket.onopen = onopen;
        // this.socket.onclose = onclose;
        // this.socket.onmessage = onmessage;
        // this.socket.onerror = onerror;
        
      };


    handleWebSocketMessage(nodeId,value){
        var el = this.container;
        el = el.querySelector(`[data-id="${nodeId}"]`);
        el = el.querySelector('input').checked = (value == "checked");
    }

    handleNodeControlChange(nodeId,value){
        this.stompClient.send("/s-house-rest-api-web-websocket/to-server", {}, JSON.stringify({'nodeId': nodeId, 'value':value}));
        console.log('nodeId:' + nodeId + ', value:' + value);
    }

    isNodeLocationExists(string) {
        let flag = false;
        let rooms = this.container.querySelectorAll('.m-flex-item');
        rooms.forEach(item => {
            if (item.querySelector('.m-portlet__head-text').textContent === string) {
                flag = true;
                break;
            };
            return flag;
        })
    }

  };