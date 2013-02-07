<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="/eclipsePhaseCharacter">
    <fo:root font-family="Liberation" font-size="12pt" text-align="left"
      xmlns:fo="http://www.w3.org/1999/XSL/Format"
      xmlns:fox="http://xml.apache.org/fop/extensions">

      <fo:layout-master-set>
        <fo:simple-page-master master-name="A4-portrait"
              page-height="29.7cm" page-width="21.0cm" margin-bottom="0mm" margin-left="0in" margin-right="0in" margin-top="0mm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="A4-portrait">
        <fo:flow flow-name="xsl-region-body">
          <fo:block-container absolute-position="absolute"
            top="2mm" left="0mm" width="21cm" height="29.7cm"
            background-image="xsl/fo/images/eclipse_phase_v1_fond_recto.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!-- Nom et attributs generaux -->
          
          <fo:block-container absolute-position="absolute" top="9mm" left="15mm">
            <fo:block  font-size="21pt"><xsl:value-of select="name"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25.5mm" left="50mm">
            <fo:block  font-size="12pt"><xsl:value-of select="background"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="36.5mm" left="50mm">
            <fo:block  font-size="12pt"><xsl:value-of select="faction"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="47.5mm" left="50mm">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/type"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="58.5mm" left="50mm">
            <fo:block  font-size="12pt"><xsl:value-of select="gender"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="69.5mm" left="50mm">
            <fo:block  font-size="12pt"><xsl:value-of select="actualAge"/></fo:block>
          </fo:block-container>
          
          <!-- Aptitudes -->
          
          <fo:block-container absolute-position="absolute" top="45mm" left="121mm" text-align="center">
			 <fo:block  font-size="12pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
		
					<fo:table-body>
						<fo:table-row height="6.5mm">
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='INT']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SOM']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='WIL']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='REF']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SAV']/@base"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5.5mm">
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='INT']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SOM']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='WIL']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='REF']/@morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SAV']/@morphBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='COO']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='COO']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='INT']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='SOM']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='WIL']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='REF']"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block font-weight="bold"><xsl:value-of select="aptitudes/aptitude[@key='SAV']"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
            </fo:block>
          </fo:block-container>
          
          <!-- Donnees chiffrees individuelles -->
           <fo:block-container absolute-position="absolute" top="71mm" left="105.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="moxie"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="initiative"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/speed"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="damageBonus"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="110.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/woundThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="121mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/durability/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="131.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/deathRating"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="traumaThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="lucidity"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="insanityRating"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="67.5mm" left="193mm" width="10mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/aptitudeMax/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="73.5mm" left="193mm" width="10mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/speedModifier/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="79.5mm" left="193mm" width="10mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/movementRate"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="88.5mm" left="185mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="experience"/></fo:block>
          </fo:block-container>
         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
</xsl:stylesheet>