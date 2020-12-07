<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Hallgatók</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Hallgató keresztneve</th>
						<th>Hallgató vezetékneve</th>
						<th>Beceneve</th>
						<th>Fizetése</th>
					</tr>
					<xsl:for-each select="osztaly/alkalmazott">
						<tr>
							<td>
								<xsl:value-of select="keresztnev" />
							</td>
							<td>
								<xsl:value-of select="vezeteknev" />
							</td>
							<td>
								<xsl:value-of select="becenev"/>
							</td>
							<td>
								<xsl:value-of select="fizetes"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>