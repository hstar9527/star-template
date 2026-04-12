<!--#set(List<Book> books)-->
<html>
<body>
	<p>Template Demo</p>
	<!--#if(books)-->
	<table>
		<tr>
			<td>序号</td>
			<td>名称</td>
		</tr>
		<!--#for(Book book : books)-->
		<tr>
			<td>${for.count}</td>
			<td>${book.title}</td>
		</tr>
		<!--#end-->
	</table>
	<!--#end-->
</body>
</html>