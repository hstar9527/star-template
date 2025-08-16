<!--#set(List<Book> books)-->
<html>
<body>
	<p>HTTL SpringMVC Demo</p>
	<!--#if(books)-->
	<table>
		<tr>
			<td>序号</td>
			<td>${"book".message}</td>
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