<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>LISTADO DE CANCIONES</title>
            </head>
            <body>
                <h1>LISTA DE CANCIONES</h1>
                <table border='1'>
                    <tr>
                        <th>ID</th>
                        <th>Año</th>
                        <th>Título</th>
                        <th>Artista</th>
                        <th>Duración</th>
                        <th>Español</th>
                    </tr>
                    <xsl:apply-templates select="Canciones/Cancion"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="Cancion">
        <tr>
            <td><xsl:value-of select="ID"/></td>
            <td><xsl:value-of select="Año"/></td>
            <td><xsl:value-of select="Título"/></td>
            <td><xsl:value-of select="Artista"/></td>
            <td><xsl:value-of select="Duración"/></td>
            <td><xsl:value-of select="Español"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
