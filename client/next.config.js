/** @type {import('next').NextConfig} */
const nextConfig = {
    webpack: (config) => {
        config.resolve.fallback = {
          ...config.resolve.fallback,
          net: false,
        };
      
        return config;
      },
};

module.exports = nextConfig;