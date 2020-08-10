<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Chinese Chess</title>
  	<link rel="stylesheet" href="js/vue/element-ui-2.13.2.css">
	<style>
		[v-cloak]{
			display: none;
		}
	</style>
	<script src="js/vue/vue-2.6.11.js" type="text/javascript"></script>
	<script src="js/vue/element-ui-2.13.2.js" charset="UTF-8"></script>
	<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>
<div id="app" v-cloak>
	<el-row style="padding-bottom: 4px;z-index: 1;
			position: relative;">
				<el-col :span="12">&nbsp;
				</el-col>
	</el-row>
</div>
<script>

var vm = new Vue({
	el: '#app',
	data() {
		return {
			activeName: 'holdee',
		}
	},
	methods: {
				
	}
});
</script>
</body>
</html>