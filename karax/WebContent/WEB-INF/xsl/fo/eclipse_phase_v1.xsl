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
           <fo:block-container absolute-position="absolute" top="79.5mm" left="173mm" width="30mm" text-align="right">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/movementRate"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="88.5mm" left="173mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="availableExperience"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="88.5mm" left="185mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="experience"/></fo:block>
          </fo:block-container>
          
          <!--  Traits -->
          
           <fo:block-container absolute-position="absolute" top="87mm" left="8mm" width="28mm" text-align="left">
           	<xsl:for-each select="traits/trait[@amount &gt; 0]">
	            <fo:block  font-size="7pt"><xsl:value-of select="@display"/>(<xsl:value-of select="@amount"/>)</fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="87mm" left="37mm" width="28mm" text-align="left">
           	<xsl:for-each select="traits/trait[@amount &lt;  0]">
	            <fo:block  font-size="7pt"><xsl:value-of select="@display"/>(<xsl:value-of select="@amount"/>)</fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!--  Motivations -->
          
           <fo:block-container absolute-position="absolute" top="87mm" left="69mm" width="28mm" text-align="left">
           	<xsl:for-each select="motivations/motivation">
	            <fo:block  font-size="7pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!-- Active Skills 1st column -->
          
           <fo:block-container absolute-position="absolute" top="120.5mm" left="8mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
			           	<xsl:for-each select="activeskills/skill[@category='COMBAT']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
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
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
						<fo:table-row height="3.82mm">
							<fo:table-cell><fo:block/></fo:table-cell>
						</fo:table-row>
			           	<xsl:for-each select="activeskills/skill[@category='MENTAL']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
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
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
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
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
								</fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
          </fo:block-container>

          <!-- Active Skills 2nd column -->

           <fo:block-container absolute-position="absolute" top="120.5mm" left="110mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
 			           	<xsl:for-each select="activeskills/skill[@category='SOCIAL']">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
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

           <fo:block-container absolute-position="absolute" top="185.7mm" left="110mm" width="94mm" text-align="center">
 			 <fo:block  font-size="8pt">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="29mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="11mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="22mm" />
 					<fo:table-body>
 			           	<xsl:for-each select="knowledgeskills/skill">
							<fo:table-row height="3.82mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@linkedAptitude"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@base"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@morphBonus"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block text-align="left">
									<xsl:for-each select="extra"><xsl:value-of select="."/></xsl:for-each>
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
 		   
 		             <!-- Donnees chiffrees Muse -->
 		             
           <fo:block-container absolute-position="absolute" top="264.5mm" left="111.7mm" width="9mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='COG']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='COO']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='INT']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="264.5mm" left="142.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='REF']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="116.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='SAV']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="127mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='SOM']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="273.5mm" left="137.5mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/aptitudes/aptitude[@key='WIL']"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="282.5mm" left="121.7mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/traumaThreshold"/></fo:block>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="282.5mm" left="132.2mm" width="10mm" text-align="center">
            <fo:block  font-size="12pt"><xsl:value-of select="muse/lucidity"/></fo:block>
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
							<fo:table-row height="3.1mm">
								<fo:table-cell><fo:block text-align="left"><xsl:value-of select="@key"/></fo:block></fo:table-cell>
								<fo:table-cell><fo:block><xsl:value-of select="@total"/></fo:block></fo:table-cell>
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
            background-image="xsl/fo/images/eclipse_phase_v1_fond_verso.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!--  Pouvoirs Psi -->
           <fo:block-container absolute-position="absolute" top="20mm" left="85mm" width="55mm" text-align="left">
           	<xsl:for-each select="psiSleights/sleight[position() &lt;= 3]">
	            <fo:block  font-size="8pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="20mm" left="145mm" width="55mm" text-align="left">
           	<xsl:for-each select="psiSleights/sleight[position() &gt; 3]">
	            <fo:block  font-size="8pt"><xsl:value-of select="@display"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
          <!--  ID  -->

          <fo:block-container absolute-position="absolute" top="39mm" left="10mm" width="56mm" text-align="left" border-style="1">
           	<xsl:apply-templates select="identities/identity[1]"/>
           	<fo:block/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="39mm" left="78mm" width="56mm" text-align="left" border-style="1">
           	<xsl:apply-templates select="identities/identity[2]"/>
           	<fo:block/>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="39mm" left="147mm" width="56mm" text-align="left" border-style="1">
           	<xsl:apply-templates select="identities/identity[3]"/>
           	<fo:block/>
          </fo:block-container>
          
          <!-- Traits de Morphe -->
          
           <fo:block-container absolute-position="absolute" top="220mm" left="10mm" width="55mm" text-align="left" >
           	<xsl:for-each select="currentMorph/traits/trait">
	            <fo:block  font-size="8pt"><xsl:value-of select="@key"/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>

          <!--  Ameliorations de Morphe  -->
          
           <fo:block-container absolute-position="absolute" top="220mm" left="77mm" width="55mm" text-align="left">
           	<xsl:for-each select="currentMorph/enhancements/enhancement[position() &lt;= 13]">
	            <fo:block  font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
           <fo:block-container absolute-position="absolute" top="220mm" left="137mm" width="55mm" text-align="left">
           	<xsl:for-each select="currentMorph/enhancements/enhancement[position() &gt; 13]">
	            <fo:block  font-size="8pt"><xsl:value-of select="."/></fo:block>
           	</xsl:for-each>
           	<fo:block/>
          </fo:block-container>
          
         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  
  <xsl:template match="identity">
	<fo:block>ID: <xsl:value-of select="name"/></fo:block>
	<fo:block  font-size="8pt" space-before="2mm">
		<fo:table table-layout="fixed" width="100%" text-align="right">
			<fo:table-column column-width="20mm" />
			<fo:table-column column-width="30mm" />
			<fo:table-body>
				<fo:table-row height="5.8mm">
					<fo:table-cell padding-right="2mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='A']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-right="3.5mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='G']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.5mm">
					<fo:table-cell padding-right="4mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='C']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-right="1.5mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='I']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.5mm">
					<fo:table-cell padding-right="4mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='E']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-right="1.5mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key='R']"/></fo:block></fo:table-cell>
				</fo:table-row>
				<fo:table-row height="5.5mm">
					<fo:table-cell padding-right="2mm"><fo:block><xsl:value-of select="reputations/reputation[@key='F']"/></fo:block></fo:table-cell>
					<fo:table-cell padding-right="3.5mm" border-style="1"><fo:block><xsl:value-of select="reputations/reputation[@key!='A' and @key!='C' and @key!='E' and @key!='F' and @key!='G' and @key!='I' and @key!='R']"/></fo:block></fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
 	 </fo:block>
     <fo:block border-style="1"><xsl:value-of select="notes"/></fo:block>
  </xsl:template>
  
</xsl:stylesheet>