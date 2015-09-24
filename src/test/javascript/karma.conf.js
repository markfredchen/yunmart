// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-09-04 using
// generator-karma 0.9.0

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine'],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      '../main/web/bower_components/jquery/dist/jquery.js',
      '../main/web/bower_components/angular/angular.js',
      '../main/web/bower_components/angular-animate/angular-animate.js',
      '../main/web/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      '../main/web/bower_components/angular-bootstrap-file-field/dist/angular-bootstrap-file-field.min.js',
      '../main/web/bower_components/angular-cookies/angular-cookies.js',
      '../main/web/bower_components/angular-dynamic-locale/src/tmhDynamicLocale.js',
      '../main/web/bower_components/angular-image-crop/image-crop.js',
      '../main/web/bower_components/angular-local-storage/dist/angular-local-storage.js',
      '../main/web/bower_components/angular-messages/angular-messages.js',
      '../main/web/bower_components/angular-resource/angular-resource.js',
      '../main/web/bower_components/angular-sanitize/angular-sanitize.js',
      '../main/web/bower_components/angular-touch/angular-touch.js',
      '../main/web/bower_components/angular-translate/angular-translate.js',
      '../main/web/bower_components/messageformat/messageformat.js',
      '../main/web/bower_components/angular-translate-interpolation-messageformat/angular-translate-interpolation-messageformat.js',
      '../main/web/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
      '../main/web/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      '../main/web/bower_components/angular-ui-router/release/angular-ui-router.js',
      '../main/web/bower_components/bootstrap-sass-official/assets/javascripts/bootstrap.js',
      '../main/web/bower_components/angular-mocks/angular-mocks.js',
      // endbower
      '../main/web/scripts/app.js',
      '../main/web/scripts/**/*.js',
      '../test/javascript/unit/**/*.js'
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      'PhantomJS'
    ],

    // Which plugins to enable
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine'
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
