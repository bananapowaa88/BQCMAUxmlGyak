<?xml version="1.0" encoding="UTF-8"?>
  <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0" xmlns:autokBQCMAU="autokBQCMAU.xml">
    <xsl:template match="auto"  >
       <xsl:value-of select="@rsz"/>
    </xsl:template>
</xsl:stylesheet>