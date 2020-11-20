const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, "../"+"main/resources/static"),
    publicPath: process.env.NODE_ENV === 'production' ? '/sigma-vue' : '/',
    devServer: {
        proxy: {
            '/admin': {
                target: 'http://localhost:9050',
                ws: true,
                changeOrigin: true
            }
        }
    }
}