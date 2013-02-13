<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:variable name="labelColor">#005e20</xsl:variable>
  
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
            background-image="xsl/eclipse_phase_v1/images/eclipse_phase_v1_fond_recto.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!-- Nom et attributs generaux -->
          
          <fo:block-container absolute-position="absolute" top="9mm" left="15mm">
            <fo:block font-family="Electrolize" font-size="21pt"><xsl:value-of select="name"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-family="Electrolize" font-size="12pt"><xsl:value-of select="$label.background"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="background"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="36.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-family="Electrolize" font-size="12pt"><xsl:value-of select="$label.faction"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="36.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="faction"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="47.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-family="Electrolize" font-size="12pt"><xsl:value-of select="$label.morph"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="47.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/type"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="58.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-family="Electrolize" font-size="12pt"><xsl:value-of select="$label.gender"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="58.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="gender"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="69.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-family="Electrolize" font-size="12pt"><xsl:value-of select="$label.actualAge"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="69.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="actualAge/@amount"/></fo:block>
          </fo:block-container>
          
          <!-- Aptitudes -->
          
          <fo:block-container absolute-position="absolute" top="39.5mm" left="80mm" text-align="center">
			 <fo:block  font-size="12pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="41mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
					<fo:table-column column-width="10.5mm" />
		
					<fo:table-body>
						<fo:table-row height="5.5mm" color="{$labelColor}" font-size="10pt">
							<fo:table-cell><fo:block font-size="14pt" font-family="Electrolize" text-align="right" margin-right="1mm"><xsl:value-of select="$label.aptitudes"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.COG"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.COO"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.INT"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.SOM"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.WIL"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.REF"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="$label.SAV"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="6.0mm">
							<fo:table-cell><fo:block font-size="10pt" font-family="Electrolize" space-before="-5mm" text-align="right" color="{$labelColor}" margin-right="1mm"><xsl:value-of select="$label.base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COG']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='INT']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SOM']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='WIL']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='REF']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SAV']/@base"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="6mm">
							<fo:table-cell><fo:block font-size="10pt" font-family="Electrolize" text-align="right" color="{$labelColor}" margin-right="1mm"><xsl:value-of select="$label.morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COG']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='COO']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='INT']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SOM']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='WIL']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='REF']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block><xsl:value-of select="aptitudes/aptitude[@key='SAV']/@effectiveMorphBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell><fo:block font-size="10pt" font-family="Electrolize" text-align="right" color="{$labelColor}" margin-right="1mm"><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
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
           <fo:block-container absolute-position="absolute" top="77mm" left="139mm" width="30mm" text-align="center">
            <fo:block  font-family="Electrolize" font-size="14pt" color="{$labelColor}"><xsl:value-of select="$label.STATS"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="66.7mm" left="105.5mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.MOX"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="105.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="moxie"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="66.7mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.INIT"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="initiative"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="66.7mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.SPD"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/speed"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="66.5mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.DB"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="71mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="damageBonus"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="76.2mm" left="110.5mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.WT"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="110.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/woundThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="76.2mm" left="121mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.DUR"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="121mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/durability"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="76.2mm" left="131.5mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.DR"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80.5mm" left="131.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/deathRating"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="85.2mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.TT"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="116mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="traumaThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="85.2mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.LUC"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="126.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="lucidity"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="85.2mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.IR"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89.5mm" left="137mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="insanityRating"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="68mm" left="169mm" width="30mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.aptitudeMax"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="67.5mm" left="193mm" width="10mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/aptitudeMax/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="74mm" left="169mm" width="30mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.speedMod"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="73.5mm" left="193mm" width="10mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/speedModifier/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="80mm" left="169mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.movement"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="79.5mm" left="173mm" width="30mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/movementRate"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89mm" left="166mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.rez"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="88.5mm" left="173mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="availableExperience"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="89mm" left="196mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.totalRez"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="88.5mm" left="185mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="experience"/></fo:block>
          </fo:block-container>

           <fo:block-container absolute-position="absolute" top="99mm" left="108mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.damage"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="99mm" left="142mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.wound"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="99mm" left="158mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.stress"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="99mm" left="191mm" width="20mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="10pt" color="{$labelColor}"><xsl:value-of select="$label.trauma"/></fo:block>
          </fo:block-container>
          
          <!--  Traits -->
          
           <fo:block-container absolute-position="absolute" top="81.5mm" left="10mm" width="50mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="9pt" color="{$labelColor}"><xsl:value-of select="$label.traits"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="87mm" left="8mm" width="28mm" text-align="left">
           	<xsl:for-each select="traits/trait[@amount &gt; 0]">
	            <fo:block  font-size="7pt"><xsl:value-of select="@display"/>
	            <xsl:if test="@descriptor">
	            	- <xsl:value-of select="@descriptor"></xsl:value-of>
	            </xsl:if>
	            <xsl:if test="@level">
	            	(<xsl:value-of select="@level"></xsl:value-of>)
	            </xsl:if>
	            </fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="87mm" left="37mm" width="28mm" text-align="left">
           	<xsl:for-each select="traits/trait[@amount &lt;  0]">
	            <fo:block  font-size="7pt"><xsl:value-of select="@display"/>
	            <xsl:if test="@descriptor">
	            	- <xsl:value-of select="@descriptor"></xsl:value-of>
	            </xsl:if>
	            <xsl:if test="@level">
	            	(<xsl:value-of select="@level"></xsl:value-of>)
	            </xsl:if>
	            </fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!--  Motivations -->
          
           <fo:block-container absolute-position="absolute" top="81.5mm" left="69mm" width="50mm" text-align="left">
            <fo:block font-family="Electrolize" font-size="9pt" color="{$labelColor}"><xsl:value-of select="$label.motivations"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="87mm" left="69mm" width="28mm" text-align="left">
           	<xsl:for-each select="motivations/motivation">
	            <fo:block  font-size="7pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!-- Active Skills 1st column -->
          
           <fo:block-container absolute-position="absolute" top="108.5mm" left="8mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10.7mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
						<fo:table-row height="8.5mm" font-family="Electrolize" font-size="7pt" color="{$labelColor}">
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.activeSkills"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.linkedAptitude"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.base"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.specOtherBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3.4mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="activeskills/skill[@category='COMBAT']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="3.82mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="activeskills/skill[@category='MOVEMENT']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="3.6mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="activeskills/skill[@category='MENTAL']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="3.82mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="activeskills/skill[@category!='COMBAT' and @category!='SOCIAL' and @category!='MENTAL' and @category!='MOVEMENT']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="3.82mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="psiskills/skill">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
          </fo:block-container>

          <!-- Active Skills 2nd column -->

           <fo:block-container absolute-position="absolute" top="108.5mm" left="110mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10.7mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
						<fo:table-row height="8.5mm" font-family="Electrolize" font-size="7pt" color="{$labelColor}">
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.activeSkills"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.linkedAptitude"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.base"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.specOtherBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3.4mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
 			           	<xsl:for-each select="activeskills/skill[@category='SOCIAL']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="1mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
 					</fo:table-body>
 				</fo:table>
 			</fo:block>
 		   </fo:block-container>

           <!-- Knowledge skills -->

           <fo:block-container absolute-position="absolute" top="173.7mm" left="110mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10.7mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
						<fo:table-row height="8.5mm" font-family="Electrolize" font-size="7pt" color="{$labelColor}">
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.knowledgeSkills"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.linkedAptitude"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.base"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.morphBonus"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell display-align="center"><fo:block text-align="left"><xsl:value-of select="$label.specOtherBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3.4mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
 			           	<xsl:for-each select="knowledgeskills/skill">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left" line-height="3.75mm">
									<xsl:for-each select="extra"><xsl:if test="position()&gt;1">/&#160;</xsl:if><xsl:value-of select="."/>&#160;</xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
 					</fo:table-body>
 				</fo:table>
 			</fo:block>
 		   </fo:block-container>
 		   
 		             <!-- Donnees chiffrees Muse -->
           <fo:block-container absolute-position="absolute" top="270mm" left="142.5mm" width="30mm" text-align="center">
            <fo:block font-family="Electrolize" font-size="14pt" color="{$labelColor}"><xsl:value-of select="$label.MUSE"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="260.5mm" left="111.3mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.COG"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="111.3mm" width="9mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='COG']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="260.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.COO"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='COO']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="260.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.INT"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='INT']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="260.5mm" left="142.7mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.REF"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="142.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='REF']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="269.5mm" left="116.5mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.SAV"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="116.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='SAV']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="269.5mm" left="127mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.SOM"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="127mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='SOM']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="269.5mm" left="137.5mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.WIL"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="137.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='WIL']/@amount"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="278.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.TT"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="282.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/traumaThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="278.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.LUC"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="282.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/lucidity"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="278.5mm" left="142.7mm" width="10mm" text-align="center">
            <fo:block  font-size="8pt" color="{$labelColor}"><xsl:value-of select="$label.IR"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="282.5mm" left="142.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/insanityRating"/></fo:block>
          </fo:block-container>
 		   
           <!-- Muse skills -->
           <fo:block-container absolute-position="absolute" top="257mm" left="175mm" width="26mm" text-align="center">
 			 <fo:block  font-size="6pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="21mm" />
					<fo:table-column column-width="5mm" />
 					<fo:table-body>
 			           	<xsl:for-each select="muse/skills/skill">
							<fo:table-row height="2.9mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@amount"/></fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="1mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
 					</fo:table-body>
 				</fo:table>
 			</fo:block>
 		   </fo:block-container>

			<!-- Verso -->
 		   <fo:block  page-break-after="always"/>
          <fo:block-container absolute-position="absolute"
            top="2mm" left="0mm" width="21cm" height="29.7cm"
            background-image="xsl/eclipse_phase_v1/images/eclipse_phase_v1_fond_verso.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!--  Backup -->
           <fo:block-container absolute-position="absolute" top="14.7mm" left="10mm" width="55mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.backupNotes"/></fo:block>
          </fo:block-container>

          <!--  Contacts -->
          
           <fo:block-container absolute-position="absolute" top="14.7mm" left="78mm" width="55mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.contacts"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="21.1mm" left="80mm" width="55mm" text-align="left">
           	<xsl:for-each select="contacts/contact[position() &lt;= 3]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="14.8mm" left="140mm" width="55mm" text-align="left">
           	<xsl:for-each select="contacts/contact[position() &gt; 3]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!--  ID  -->

          <fo:block-container absolute-position="absolute" top="39mm" left="10mm" width="56mm" text-align="left">
           	<xsl:apply-templates select="identities/identity[1]"/>
           	<fo:block/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="39mm" left="78mm" width="56mm" text-align="left">
           	<xsl:apply-templates select="identities/identity[2]"/>
           	<fo:block/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="39mm" left="147mm" width="56mm" text-align="left">
           	<xsl:apply-templates select="identities/identity[3]"/>
           	<fo:block/>
          </fo:block-container>
          
          <!--  Armure -->
          
          <fo:block-container absolute-position="absolute" top="92.6mm" left="9mm" width="80mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.armor"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="102mm" left="5mm" width="16.5mm" text-align="right">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.energyArmor"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="102mm" left="43.5mm" width="80mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.kineticArmor"/></fo:block>
          </fo:block-container>
          <fo:block-container font-family="Electrolize" absolute-position="absolute" top="110mm" left="9mm" width="50mm" text-align="left" font-size="8pt">
	  		  <fo:block wrap-option="wrap">	_______________________________ _______________________________ _______________________________
		     	_______________________________
		      </fo:block>
		  </fo:block-container>
     
          <!--  Armes melee  -->

          <fo:block-container absolute-position="absolute" top="90mm" left="67mm" width="38mm" text-align="left">
           	<xsl:call-template name="meleeWeapon"/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="90mm" left="114mm" width="38mm" text-align="left">
           	<xsl:call-template name="meleeWeapon"/>
          </fo:block-container>
          
          <!--  Pouvoirs Psi -->
          
          <fo:block-container absolute-position="absolute" top="94mm" left="164mm" width="38mm" text-align="left">
           	<fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.psiSleights"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="101mm" left="164mm" width="38mm" text-align="left">
         	 <xsl:for-each select="psiSleights/sleight">
	            <fo:block  font-size="8pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!--  Armes distance  -->

          <fo:block-container absolute-position="absolute" top="130mm" left="7mm" width="58mm" text-align="left">
           	<xsl:call-template name="rangedWeapon"/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="130mm" left="76.3mm" width="58mm" text-align="left">
           	<xsl:call-template name="rangedWeapon"/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="130mm" left="144mm" width="58mm" text-align="left">
           	<xsl:call-template name="rangedWeapon"/>
          </fo:block-container>
          
          <!-- Equipement -->
          
          <fo:block-container absolute-position="absolute" top="169.5mm" left="9mm" width="80mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.gear"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="174.7mm" left="15mm" width="60mm" text-align="left" >
           	<xsl:for-each select="gear/item[position() &lt; 11]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="174.7mm" left="77mm" width="60mm" text-align="left" >
           	<xsl:for-each select="gear/item[position() &gt; 10 and position() &lt; 21]">
	            <fo:block  space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="174.7mm" left="140mm" width="60mm" text-align="left" >
           	<xsl:for-each select="gear/item[position() &gt; 20]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>


          <!-- Traits de Morphe -->
          
           <fo:block-container absolute-position="absolute" top="215mm" left="9mm" width="80mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.morphTraits"/></fo:block>
          </fo:block-container>

           <fo:block-container absolute-position="absolute" top="221.8mm" left="10mm" width="55mm" text-align="left" >
           	<xsl:for-each select="currentMorph/traits/trait">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>

          <!--  Ameliorations de Morphe  -->
          
           <fo:block-container absolute-position="absolute" top="215mm" left="76mm" width="100mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.morphEnhancements"/></fo:block>
          </fo:block-container>

           <fo:block-container absolute-position="absolute" top="221.8mm" left="77mm" width="55mm" text-align="left">
           	<xsl:for-each select="currentMorph/enhancements/enhancement[position() &lt;= 13]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="221.8mm" left="140mm" width="55mm" text-align="left">
           	<xsl:for-each select="currentMorph/enhancements/enhancement[position() &gt; 13]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!-- Logiciels embarques -->
          
           <fo:block-container absolute-position="absolute" top="270.5mm" left="10mm" width="55mm" text-align="left">
	            <fo:block  font-family="Electrolize" color="{$labelColor}" font-size="8pt"><xsl:value-of select="$label.embeddedSoftware"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="275.4mm" left="15mm" width="60mm" text-align="left" >
           	<xsl:for-each select="software/item[position() &lt; 6]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="275.4mm" left="77mm" width="60mm" text-align="left" >
           	<xsl:for-each select="software/item[position() &gt; 5 and position() &lt; 11]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="275.4mm" left="140mm" width="60mm" text-align="left" >
           	<xsl:for-each select="software/item[position() &gt; 10]">
	            <fo:block space-before="-0.2mm" font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>

         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  
  <xsl:template match="identity">
	<fo:block><xsl:value-of select="$label.ID"/>: <xsl:value-of select="name"/></fo:block>
	<fo:block font-size="8pt" space-before="1.5mm">
		<fo:table table-layout="fixed" width="100%" text-align="left">
			<fo:table-column column-width="32mm" />
			<fo:table-column column-width="20mm" />
			<fo:table-body>
				<fo:table-row height="6mm">
					<fo:table-cell padding-left="4mm"><fo:block><xsl:value-of select="$label.REPA"/>: <xsl:value-of select="reputations/reputation[@key='A-rep']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-left="1mm"><fo:block><xsl:value-of select="$label.REPG"/>: <xsl:value-of select="reputations/reputation[@key='G-rep']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.6mm">
					<fo:table-cell padding-left="2mm"><fo:block><xsl:value-of select="$label.REPC"/>: <xsl:value-of select="reputations/reputation[@key='C-rep']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-left="3mm"><fo:block><xsl:value-of select="$label.REPI"/>: <xsl:value-of select="reputations/reputation[@key='I-rep']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.8mm">
					<fo:table-cell padding-left="2mm"><fo:block><xsl:value-of select="$label.REPE"/>: <xsl:value-of select="reputations/reputation[@key='E-rep']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-left="3mm"><fo:block><xsl:value-of select="$label.REPR"/>: <xsl:value-of select="reputations/reputation[@key='R-rep']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.5mm">
					<fo:table-cell padding-left="4mm"><fo:block><xsl:value-of select="$label.REPF"/>: <xsl:value-of select="reputations/reputation[@key='F-rep']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-left="1mm"><fo:block><xsl:value-of select="reputations/reputation[@key!='A' and @key!='C' and @key!='E' and @key!='F' and @key!='G' and @key!='I' and @key!='R']"/></fo:block></fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
 	 </fo:block>
     <fo:block font-size="8pt" width="54mm" wrap-option="wrap">
     <xsl:choose>
     	<xsl:when test="notes"><xsl:value-of select="notes"/></xsl:when>
     	<xsl:otherwise>_______________________________________ _______________________________________ _______________________________________
     	_______________________________________</xsl:otherwise>
     </xsl:choose>
     	
     </fo:block>
  </xsl:template>
  
  <xsl:template name="meleeWeapon">
        <fo:block-container absolute-position="absolute" top="2.5mm" left="2mm" text-align="left">
	        <fo:block  font-family="Electrolize" font-size="8pt" wrap-option="wrap">
	        	<fo:inline color="{$labelColor}"><xsl:value-of select="$label.meleeWeapon"/>:</fo:inline> _________ _______________________
	        </fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="13mm" left="2mm" text-align="left">
	           <fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.weaponSkill"/>: _______</fo:block>
	           <fo:block space-before="2mm" font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.weaponDV"/>: ___________
	           <xsl:value-of select="$label.weaponAP"/>: _____</fo:block>
	           <fo:block space-before="2mm" font-family="Electrolize" font-size="8pt">_______________________
	           _______________________ _______________________</fo:block>
        </fo:block-container>
  </xsl:template>
  
  <xsl:template name="rangedWeapon">
        <fo:block-container absolute-position="absolute" top="0.5mm" left="2mm" text-align="left">
	        <fo:block  font-family="Electrolize" font-size="8pt" wrap-option="wrap">
	        	<fo:inline color="{$labelColor}"><xsl:value-of select="$label.rangedWeapon"/>:</fo:inline> ________ _______________________
	        </fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="2.5mm" left="40mm" text-align="left">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.weaponSkill"/>: ____</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="9mm" left="1mm" text-align="right" width="15mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.weaponRange"/>:</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="9mm" left="17mm" text-align="center" width="10mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.rangeShort"/>:____ [0]</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="9mm" left="26mm" text-align="center" width="10mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.rangeMedium"/>:____ [-10]</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="9mm" left="35mm" text-align="center" width="10mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.rangeLong"/>:____ [-20]</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="9mm" left="44mm" text-align="center" width="10mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.rangeExtreme"/>:____ [-30]</fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="16mm" left="1.7mm" text-align="right" width="6mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.SS"/></fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="16mm" left="9mm" text-align="left" width="6mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.SA"/></fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="19.5mm" left="1.7mm" text-align="right" width="6mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.BF"/></fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="19.5mm" left="9mm" text-align="left" width="6mm">
        	<fo:block font-family="Electrolize" font-size="8pt"><xsl:value-of select="$label.FA"/></fo:block>
        </fo:block-container>
        <fo:block-container absolute-position="absolute" top="16.5mm" left="2mm" text-align="right" width="52mm">
        	<fo:block font-family="Electrolize" font-size="8pt">
        		<xsl:value-of select="$label.weaponDV"/>:_______________ <xsl:value-of select="$label.weaponAP"/>:____
        		___________________________ _____________________________ __________________________________ __________________________________
        		
        	</fo:block>
        </fo:block-container>
  </xsl:template>
  
</xsl:stylesheet>