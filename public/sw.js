//Add this below content to your HTML page, or add the js file to your page at the very top to register service worker
if (navigator.serviceWorker.controller) {
  console.log('[PWA Info] active service worker found, no need to register')
} else {
  //Register the ServiceWorker
  navigator.serviceWorker.register('service-worker.js', {
    scope: '/'
  }).then(function(reg) {
    console.log('Service worker has been registered for scope:'+ reg.scope);
  });
