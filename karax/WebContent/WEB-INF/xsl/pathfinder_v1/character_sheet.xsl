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
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="levels"/></fo:block>
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
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="initiative/@miscbonus"/></fo:block></fo:table-cell>
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
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="initiative/@miscbonus"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Sauvegarde  -->

          <fo:block-container absolute-position="absolute" top="136mm" left="15mm" width="30mm">
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
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@ability"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@magic"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='FOR']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.saveREF"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@ability"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@magic"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='REF']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" color="white" background-color="black"><xsl:value-of select="$label.saveWIL"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@base"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@ability"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@magic"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="saves/save[@key='WIL']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>
          
          <!-- RAF: HP, BAB, BMO, DMD, Compétences -->
          
<!-- 

          <fo:block-container absolute-position="absolute" top="9mm" left="15mm">
            <fo:block font-size="21pt"><xsl:value-of select="name"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-size="12pt"><xsl:value-of select="$label.background"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="background"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="36.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-size="12pt"><xsl:value-of select="$label.faction"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="36.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="faction"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="47.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-size="12pt"><xsl:value-of select="$label.morph"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="47.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="currentMorph/type"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="58.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-size="12pt"><xsl:value-of select="$label.gender"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="58.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="gender"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="69.5mm" left="19mm" color="{$labelColor}">
            <fo:block font-size="12pt"><xsl:value-of select="$label.actualAge"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="69.5mm" left="49mm">
            <fo:block  font-size="12pt"><xsl:value-of select="actualAge/@amount"/></fo:block>
          </fo:block-container>
          
  -->

         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  

  
</xsl:stylesheet>
