var restify = require('restify');

var client = restify.createJsonClient({
  url: 'http://127.0.0.1:8080',
  version: '*'
});
/*
client.get('/gg/setting', function(err, req, res, obj) {
  console.log('%j', obj);
});
*/
//var mysetting = { code: "ZZ2", id: 2};
client.del('/gg/setting/2', function(err, req, res, obj) {
  console.log('%j', obj);
});

