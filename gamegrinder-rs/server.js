var persist = require("persist");

var settingModule = require("./entities/setting.js");

persist.connect(
  function(err, connection) {
    var settingEm = settingModule(connection);
    settingEm.fetchAll();
});
