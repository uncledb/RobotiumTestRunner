<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				<title>自动化运行结果</title>
				<style type="text/css">
					body{
					font-size : 12px;
					font-family : 宋体;
					color :
					black;
					}
				</style>
			</head>
			<body>
				<table border="1" bordercolor="green" style="border-collapse:collapse">
					<tr>
						<td align="center">用例</td>
						<td align="center">方法名</td>
						<td align="center">错误类型</td>
						<td align="center">程序提示</td>
						<!-- <td align="center">错误详情</td> -->
						<td align="center">用时</td>
					</tr>
					<xsl:for-each select="/testsuites/testsuite/testcase">
						<tr>
							<td align="left">
								<xsl:value-of select="@classname" />
							</td>
							<td align="left">
								<xsl:value-of select="@name" />
							</td>
							<td align="left">
								<xsl:value-of select="failure/@type" />
							</td>
							<td align="left" style="color:red">
								<xsl:value-of select="failure/@message" />
							</td>
							<!-- <td align="left"> <xsl:value-of select="failure" /> </td> -->
							<td align="left">
								<xsl:value-of select="@time" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
