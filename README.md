Приложение запускать с параметром -Dfile.commissions=path, где path - путь к xml-файлу с коммиссиями (например ~/commissions.xml).

Пример файла:
<commissions>
	<commission id="1">
		<brand>MASTERCARD</brand>
		<currency>USD</currency>
		<value>2.5</value>
	</commission>
	<commission id="2">
		<brand>MASTERCARD</brand>
		<currency>RUB</currency>
		<value>2</value>
	</commission>
	<commission id="3">
		<brand>MASTERCARD</brand>
		<currency>BYN</currency>
		<value>1.5</value>
	</commission>
	<commission id="4">
		<brand>VISA</brand>
		<currency>BYN</currency>
		<value>1.5</value>
	</commission>
</commissions>