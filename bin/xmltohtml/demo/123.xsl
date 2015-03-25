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
				<title>员工信息</title>
			</head>

			<body>
				<table width="800px" border="1" bordercolor="#000000"
					style="border-collapse:collapse">
					<tr>
						<td align="center">编号</td>
						<td align="center">姓名</td>
						<td align="center">性别</td>
						<td align="center">地址</td>
					</tr>
					<xsl:for-each select="employees/employee-list/employee">
						<tr>
							<td align="center">
								<xsl:value-of select="id" />
							</td>
							<td align="center">
								<xsl:value-of select="name" />
							</td>
							<td align="center">
								<xsl:value-of select="gender" />
							</td>
							<td align="center">
								<xsl:value-of select="address" />
							</td>
						</tr>
					</xsl:for-each>

				</table>
			</body>
		</html>

	</xsl:template>
</xsl:stylesheet>
