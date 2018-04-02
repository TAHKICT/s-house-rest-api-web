class View {


  constructor(templates) {
    this.templates = templates;
  }


  renderRoom(room) {
    
  let str =  `
    <div class="m-portlet m-portlet--mobile m-flex-item" id="${room}">
                          <div class="m-portlet__head">
                          
                              <div class="m-portlet__head-caption">
                                  <div class="m-portlet__head-title">
                                      <h3 class="m-portlet__head-text">
                                          ${room}
                                      </h3>
                                  </div>
                              </div>
                              
                          </div>
                          <div class="m-portlet__body">
                              </div>
                      </div>
    `
    return str;
  }

  renderControl(data) {

  }

  getTemplate(string) {
    return this.templates.get(string);
  }

}