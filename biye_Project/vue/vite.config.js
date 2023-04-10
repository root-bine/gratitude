import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
export default defineConfig({
  plugins: [vue()],
  // 本地服务
  server: {
    host: "0.0.0.0", // ip
    port: 8083,  // 端口号
    open: false,  // 是否自动在浏览器打开
    https: false, // 是否开启 https
    // 跨域代理配置
    proxy: {
      '/api': {
        target: 'https://blog.csdn.net/weixin_45292658',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/api/, '')
      }
    }
  }
})

