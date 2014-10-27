<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:exsl="http://exslt.org/common"
      xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:variable name="labelColor">#005e20</xsl:variable>
  
  <xsl:template match="/pathfinderCharacter">
    <fo:root font-family="Griffos" font-size="12pt" text-align="left"
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
            top="0mm" left="0mm" width="21cm" height="29.7cm"
            background-image="xsl/pathfinder_v1/images/pathfinder_v1_fond.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!-- Nom et attributs generaux -->
          
          <fo:block-container absolute-position="absolute" top="20mm" left="90mm" width="50mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="name"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.name"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="20mm" left="143mm" width="15mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="alignment"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.alignment"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="20mm" left="161mm" width="40mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="playerName"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.player"/></fo:block>
          </fo:block-container>

          <fo:block-container absolute-position="absolute" top="30mm" left="90mm" width="50mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin">
            	<xsl:for-each select="levels/level">
            		<xsl:if test="position() > 1">,&#160;</xsl:if><xsl:value-of select="@display"></xsl:value-of>&#160;<xsl:value-of select="."></xsl:value-of>
            	</xsl:for-each>
            </fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.levels"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="30mm" left="143mm" width="30mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="deity"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.deity"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="30mm" left="176mm" width="25mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="homeland"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.homeland"/></fo:block>
          </fo:block-container>

          <fo:block-container absolute-position="absolute" top="40mm" left="90mm" width="17mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="race"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.race"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="110mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="size"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.size"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="121mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="sex"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.sex"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="132mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="age"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.age"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="143mm" width="13mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="height"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.height"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="159mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="weight"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.weight"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="174mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="hair"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.hair"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="40mm" left="189mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="eyes"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.eyes"/></fo:block>
          </fo:block-container>

          <!-- Caracteristiques -->

          <fo:block-container absolute-position="absolute" top="55mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="70mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="3mm" font-size="7pt">
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongSTR"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='STR']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='STR']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongDEX"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongCON"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CON']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CON']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongINT"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='INT']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='INT']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongWIS"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='WIS']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='WIS']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.abilityLongCHA"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CHA']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CHA']/@bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
            </fo:block>
          </fo:block-container>

          <!-- Initiative  -->

          <fo:block-container absolute-position="absolute" top="110mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="80mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilityDEX"/></fo:block></fo:table-cell>
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-top="1mm" padding-left="2mm" color="white" background-color="black"><xsl:value-of select="$label.initiative"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="initiative"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="initiative/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- CA  -->

          <fo:block-container absolute-position="absolute" top="123mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="16mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.ACarmor"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.ACshield"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilityDEX"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.size"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.ACnatural"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.ACdeflection"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.armorclass"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">= 10 +</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@armor"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@shield"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@size"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@natural"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@deflection"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="initiative/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.ACtouch"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@touch"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" number-columns-spanned="4"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.ACflatfooted"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="armorclass/@flatfooted"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Sauvegarde  -->

          <fo:block-container absolute-position="absolute" top="151mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell text-align="center"><fo:block font-size="12pt" padding-left="1mm"><xsl:value-of select="$label.savingthrows"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.saveBase"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.saveAbility"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.saveMagic"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.saveFOR"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.saveREF"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.saveWIL"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- HP  -->

          <fo:block-container absolute-position="absolute" top="185mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="50mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.hitpoints"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="hitpoints"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois">&#xA0;</fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>
          
          <!-- BAB -->

          <fo:block-container absolute-position="absolute" top="195mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.bab"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="bab"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- BMC -->

          <fo:block-container absolute-position="absolute" top="205mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell text-align="center"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.babBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilitySTR"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.size"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.cmb"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="cmd"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="bab"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='STR']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="cmb/@size"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- DMC -->

          <fo:block-container absolute-position="absolute" top="218mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="16mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell text-align="center"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.babBonus"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilitySTR"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilityDEX"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.size"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.cmd"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="cmd"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">= 10 +</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="bab"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='STR']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="cmd/@size"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Deplacement -->

          <fo:block-container absolute-position="absolute" top="231mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="25mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.speed"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="speed"/> <fo:inline font-size="8pt">m/round</fo:inline></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Langues -->

        <fo:block-container absolute-position="absolute" top="245mm" left="15mm" width="100mm">
            <fo:block font-size="10pt"><xsl:value-of select="$label.languages"/>:<xsl:for-each select="languages/language"><xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="."/></xsl:for-each></fo:block>
		</fo:block-container>

          			<!-- Verso -->
 		   <fo:block  page-break-after="always"/>
          <fo:block-container absolute-position="absolute"
            top="2mm" left="0mm" width="21cm" height="29.7cm"
            background-image="xsl/pathfinder_v1/images/pathfinder_v1_fond.jpg">
            <fo:block/>
          </fo:block-container>
          
          <!-- Competences -->

          <fo:block-container absolute-position="absolute" top="15mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="60mm" />
					<fo:table-column column-width="9mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell number-columns-spanned="9" padding="1mm" text-align="center"><fo:block padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.skills"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" display-align="center" font-size="6pt">
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.skillAbility"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.skillRanks"/></fo:block></fo:table-cell>
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
						<xsl:for-each select="skills/skill">
							<fo:table-row height="3mm" font-size="8pt">
								<fo:table-cell padding-top="1mm"><fo:block font-size="8pt" font-family="Glyphicons">&#xE013;</fo:block></fo:table-cell>
								<fo:table-cell text-align="left" padding-left="2mm"><fo:block padding-bottom="-1mm" border-bottom-style="solid" border-bottom-width="thin"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>=</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@abilityBonus"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@ranks"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@miscBonus"/></fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>
          
          <!-- Armes -->

 	<xsl:for-each select="weapon">	
          <fo:block-container absolute-position="absolute" top="218mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="20mm" />
					<fo:table-column column-width="30mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" color="white" background-color="black">
							<fo:table-cell number-columns-spanned="3" padding-left="5mm"><fo:block><xsl:value-of select="$label.weapon"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.attack"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.critical"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" number-columns-spanned="3" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@attack"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@critical"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" color="white" background-color="black">
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.type"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.range"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.ammo"/></fo:block></fo:table-cell>
							<fo:table-cell number-columns-spanned="2" text-align="center"><fo:block color="white" background-color="black"><xsl:value-of select="$label.damage"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@type"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@range"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@ammo"/></fo:block></fo:table-cell>
							<fo:table-cell number-columns-spanned="2" border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@damage"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>
 	</xsl:for-each>	

          <!-- Protections -->

          <fo:block-container absolute-position="absolute" top="218mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="90mm" text-align="left">
					<fo:table-column column-width="30mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="25mm" />
					<fo:table-header>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" color="white" background-color="black">
							<fo:table-cell padding-left="5mm"><fo:block><xsl:value-of select="$label.armor"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.type"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.penalty"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.spellfailure"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.weight"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.notes"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
				 	<xsl:for-each select="armor">	
						<fo:table-row height="5mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@type"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@penalty"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@spellfailure"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@weight"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@notes"/></fo:block></fo:table-cell>
						</fo:table-row>
					  </xsl:for-each>
						<fo:table-row height="5mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>
          
 		   <fo:block  page-break-after="always"/>

          <!-- Equipement -->
          
          <fo:block-container absolute-position="absolute" top="30mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="55mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" color="white" background-color="black">
							<fo:table-cell padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.gear"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-size="6pt" font-family="Carrois"><fo:block><xsl:value-of select="$label.weight"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
				 	<xsl:for-each select="gear/item">	
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@weight"/></fo:block></fo:table-cell>
						</fo:table-row>
					  </xsl:for-each>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-right="2mm" border-style="solid" border-width="thin" text-align="right" padding-top="1mm"><fo:block><xsl:value-of select="$label.totalweight"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="sum(//@weight)"/> </fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" color="white" background-color="black">
							<fo:table-cell number-columns-spanned="2" padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.load"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadLight"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block> </fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadMedium"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block> </fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadHeavy"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block> </fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>
     
               <!-- Richesses -->
          
          <fo:block-container absolute-position="absolute" top="100mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="55mm" text-align="left">
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="40mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" color="white" background-color="black">
							<fo:table-cell number-columns-spanned="3" padding-left="3mm" font-size="10pt"><fo:block><xsl:value-of select="$label.treasure"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-left-style="solid" border-width="thin" padding-top="2mm"><fo:block><xsl:value-of select="$label.copper"/></fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" ><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" border-right-style="solid"><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-left-style="solid" border-width="thin" padding-top="2mm"><fo:block><xsl:value-of select="$label.silver"/></fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" ><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" border-right-style="solid"><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-left-style="solid" border-width="thin" padding-top="2mm"><fo:block><xsl:value-of select="$label.gold"/></fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" ><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" border-right-style="solid"><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-left-style="solid" border-width="thin" padding-top="2mm"><fo:block><xsl:value-of select="$label.platinum"/></fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" ><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
							<fo:table-cell padding="2mm" padding-bottom="-1mm" border-width="thin" border-right-style="solid"><fo:block border-width="thin" border-bottom-style="solid">&#x00A0;</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="2mm">
							<fo:table-cell number-columns-spanned="3" border-width="thin" border-left-style="solid" border-right-style="solid" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>
          
          <!-- Dons, traits et capacités -->
          
          <fo:block-container absolute-position="absolute" top="30mm" left="115mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="75mm" text-align="left">
					<fo:table-column column-width="3mm" />
					<fo:table-column column-width="70mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" color="white" background-color="black">
							<fo:table-cell number-columns-spanned="2" padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.traits"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
					 	<xsl:for-each select="racetraits/racetrait">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block>R</fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
					 	<xsl:for-each select="feats/feat">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block>D</fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
					 	<xsl:for-each select="traits/trait">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block>T</fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
					 	<xsl:for-each select="specialAbilities/specialAbility">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block>S</fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>
          
 		   <fo:block  page-break-after="always"/>

          <!-- Bloc de lanceur de sorts -->

          <fo:block-container absolute-position="absolute" top="30mm" left="15mm" width="30mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="70mm" text-align="left">
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" color="white" background-color="black">
							<fo:table-cell number-columns-spanned="5" text-align="center" padding-top="1mm" padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.spells"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell number-columns-spanned="5" text-align="center"><fo:block><xsl:value-of select="$label.domains"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="8pt" display-align="center">
							<fo:table-cell number-columns-spanned="5" text-align="center" border-width="thin" border-bottom-style="solid"><fo:block>
								<xsl:for-each select="domains/domain"><xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="."/></xsl:for-each>
							</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.knownSpells"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.spellDR"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.level"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.spellsPerDay"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.bonusSpells"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block>0</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois"><fo:block>-</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block>1</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin">-</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="6pt" display-align="center">
							<fo:table-cell text-align="center" font-family="Carrois"><fo:block>0</fo:block></fo:table-cell>
							<fo:table-cell number-columns-spanned="4" font-family="Glyphicons"><fo:block>&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;&#xE157;</fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="6pt" display-align="center">
							<fo:table-cell number-columns-spanned="5" font-family="Carrois"><fo:block><xsl:for-each select="knownspells/knownspell[@level = '0']"><xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="."/></xsl:for-each></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  

  
</xsl:stylesheet>
