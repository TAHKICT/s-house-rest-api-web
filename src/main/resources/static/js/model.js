class Model {

  constructor(settings) {
    this.settings = settings;
    this.socket = undefined;
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
        this.socket = new WebSocket(this.settings.webSocDataUrl);
        this.socket.onopen = onopen;
        this.socket.onclose = onclose;
        this.socket.onmessage = onmessage;
        this.socket.onerror = onerror;
        
      };

  };