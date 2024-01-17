// Generated using webpack-cli https://github.com/webpack/webpack-cli

const path = require("path")
const HtmlWebpackPlugin = require("html-webpack-plugin")
const MiniCssExtractPlugin = require("mini-css-extract-plugin")

const isProduction = process.env.NODE_ENV == "production"

const stylesHandler = isProduction ? MiniCssExtractPlugin.loader : "style-loader"

const config = {
	entry: "./src/index.tsx",
	output: {
		filename: "[name].[contenthash].js",
		path: path.resolve(__dirname, "build"),
		clean: true,
	},
	devServer: {
		open: true,
		host: "localhost",
		static: {
			directory: path.join(__dirname, "public"),
		},
		port: 8000,
		proxy: [
			{
				context: ["/api", "/oauth2", "/login"],
				target: "http://localhost:8080",
			},
		],
	},
	plugins: [
		new HtmlWebpackPlugin({
			title: "SaveEat",
			favicon: "src/assets/favicon.png",
		}),
		// Add your plugins here
		// Learn more about plugins from https://webpack.js.org/configuration/plugins/
	],
	module: {
		rules: [
			{
				test: /\.(ts|tsx)$/i,
				loader: "ts-loader",
				exclude: ["/node_modules/"],
			},
			{
				test: /\.css$/i,
				use: [stylesHandler, "css-loader", "postcss-loader"],
			},
			{
				test: /\.(eot|ttf|woff|woff2|png|jpg|gif)$/i,
				type: "asset",
			},

			// https://react-svgr.com/docs/webpack/
			{
				test: /\.svg$/i,
				issuer: /\.[jt]sx?$/,
				use: ["@svgr/webpack"],
			},
			// Add your rules for custom modules here
			// Learn more about loaders from https://webpack.js.org/loaders/
		],
	},
	resolve: {
		extensions: [".tsx", ".ts", ".jsx", ".js", "..."],
		alias: {
			"@": path.resolve(__dirname, "src"),
		},
	},
}

module.exports = () => {
	if (isProduction) {
		config.mode = "production"

		config.plugins.push(new MiniCssExtractPlugin({
			filename: "[name].[contenthash].css",
		}))
	} else {
		config.mode = "development"
	}
	return config
}
