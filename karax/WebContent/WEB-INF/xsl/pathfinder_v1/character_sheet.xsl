<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:exsl="http://exslt.org/common"
      xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:variable name="labelColor">#005e20</xsl:variable>
  
  <xsl:template match="/pathfinderCharacter">
    <fo:root font-family="CreditValley" font-size="14pt" text-align="left"
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
          
          <fo:block-container absolute-position="absolute" top="15mm" left="82mm" width="55mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="name"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.name"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="15mm" left="140mm" width="15mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="alignment"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.alignment"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="15mm" left="158mm" width="40mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="playerName"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.player"/></fo:block>
          </fo:block-container>

          <fo:block-container absolute-position="absolute" top="25mm" left="82mm" width="55mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin">
            	<xsl:for-each select="levels/level">
            		<xsl:if test="position() > 1">,&#160;</xsl:if><xsl:value-of select="@display"></xsl:value-of>&#160;<xsl:value-of select="."></xsl:value-of>
            	</xsl:for-each>
            </fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.levels"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25mm" left="140mm" width="30mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="deity"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.deity"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="25mm" left="173mm" width="25mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="homeland"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.homeland"/></fo:block>
          </fo:block-container>

          <fo:block-container absolute-position="absolute" top="35mm" left="82mm" width="22mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:value-of select="race"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.race"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="107mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="size"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.size"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="118mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="sex"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.sex"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="129mm" width="8mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="age"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.age"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="140mm" width="13mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="height"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.height"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="156mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="weight"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.weight"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="171mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="hair"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.hair"/></fo:block>
          </fo:block-container>
          <fo:block-container absolute-position="absolute" top="35mm" left="186mm" width="12mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin" text-align="center"><xsl:value-of select="eyes"/></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.eyes"/></fo:block>
          </fo:block-container>

          <!-- Langues -->

          <fo:block-container absolute-position="absolute" top="45mm" left="82mm" width="116mm">
            <fo:block font-size="12pt" border-after-style="solid" border-after-width="thin"><xsl:for-each select="languages/language"><xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="@display"/></xsl:for-each></fo:block>
            <fo:block font-size="7pt"><xsl:value-of select="$label.languages"/></fo:block>
          </fo:block-container>


          <!-- Caracteristiques -->

          <fo:block-container absolute-position="absolute" top="15mm" left="19mm" width="56mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" text-align="left" width="100%">
					<fo:table-column column-width="32mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="3mm" font-size="7pt">
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.bonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongSTR"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='STR']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='STR']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongDEX"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='DEX']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='DEX']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongCON"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='CON']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CON']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongINT"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='INT']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='INT']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongWIS"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='WIS']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='WIS']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.abilityLongCHA"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="abilities/ability[@key='CHA']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois"><xsl:value-of select="abilities/ability[@key='CHA']/@signedBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
            </fo:block>
          </fo:block-container>

          <!-- Deplacement -->

          <fo:block-container absolute-position="absolute" top="52mm" left="82mm" width="65mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="25mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.speed"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" font-size="8pt" background-color="white"><xsl:value-of select="speed"/> m/round</fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Initiative  -->

          <fo:block-container absolute-position="absolute" top="59mm" left="82mm" width="72mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="32mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="center">
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.total"/></fo:block></fo:table-cell>
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.abilityDEX"/></fo:block></fo:table-cell>
							<fo:table-cell ><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-top="1mm" padding-left="2mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.initiative"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" font-size="8pt" background-color="white"><xsl:value-of select="initiative"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block background-color="#d4c39b" padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" font-size="8pt"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block background-color="#d4c39b" padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" font-size="8pt"><xsl:value-of select="initiative/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Sauvegarde  -->

          <fo:block-container absolute-position="absolute" top="70mm" left="19mm" width="96mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="32mm" />
					<fo:table-column column-width="8mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="7pt" display-align="after">
							<fo:table-cell text-align="center"><fo:block font-size="10pt" padding-left="1mm"><xsl:value-of select="$label.savingthrows"/></fo:block></fo:table-cell>
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
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" font-size="10pt" background-color="white"><xsl:value-of select="$label.saveFOR"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="saves/save[@key='FOR']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='FOR']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='FOR']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='FOR']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='FOR']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" font-size="10pt" background-color="white"><xsl:value-of select="$label.saveREF"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="saves/save[@key='REF']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='REF']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='REF']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='REF']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='REF']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" font-size="10pt" background-color="white"><xsl:value-of select="$label.saveWIL"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="saves/save[@key='WIL']"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='WIL']/@baseBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='WIL']/@abilityBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='WIL']/@magicBonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="saves/save[@key='WIL']/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- BAB -->

          <fo:block-container absolute-position="absolute" top="70mm" left="100mm" width="48mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="8mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white"><xsl:value-of select="$label.bab"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center" font-size="8pt"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="baseAttackBonus"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- BMC -->

          <fo:block-container absolute-position="absolute" top="77mm" left="100mm" width="72mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="8mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
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
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" font-size="10pt" background-color="white"><xsl:value-of select="$label.cmb"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="maneuverAttack"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">=</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="baseAttackBonus/@raw"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="abilities/ability[@key='STR']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="size/@amount"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- DMC -->

          <fo:block-container absolute-position="absolute" top="87mm" left="100mm" width="88mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="8mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
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
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" font-size="10pt" background-color="white"><xsl:value-of select="$label.cmd"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="maneuverDefense"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">= 10 +</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="baseAttackBonus/@raw"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="abilities/ability[@key='STR']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="size/@amount"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>
          
          <!-- HP  -->

          <fo:block-container absolute-position="absolute" top="100mm" left="130mm" >
            <fo:block font-size="10pt">
				<fo:table table-layout="fixed" width="52mm" text-align="left">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-body>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell padding="1mm"><fo:block padding-top="1mm" font-weight="bold" border-style="solid" border-width="thin" background-color="white">&#xA0;<xsl:value-of select="$label.hitpoints"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="hitpoints"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="8mm" display-align="center">
							<fo:table-cell padding="1mm" number-columns-spanned="2" border-style="solid" border-width="thin" background-color="white"><fo:block>&#xA0;</fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>
          
                    <!-- Competences -->

          <fo:block-container absolute-position="absolute" top="120mm" left="100mm" width="84mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="9mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="5mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center">
							<fo:table-cell number-columns-spanned="12" padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" background-color="white"><xsl:value-of select="$label.skills"/></fo:block></fo:table-cell>
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
							<fo:table-cell><fo:block></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.load"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
						<xsl:for-each select="skills/skill">
						    <xsl:sort select="@display"/>
							<fo:table-row height="3mm" font-size="8pt">
								<fo:table-cell padding-top="1mm"><fo:block font-size="8pt" padding-bottom="-0.5mm" font-family="Glyphicons"><xsl:if test="@classskill = 'true'">&#xE013;</xsl:if></fo:block></fo:table-cell>
								<fo:table-cell text-align="left" padding-left="2mm" font-size="10pt"><fo:block padding-bottom="-0.5mm" border-bottom-style="solid" border-bottom-width="thin"><xsl:value-of select="@display"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>=</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@abilityBonus"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@ranks"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@miscBonus"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@armorPenalty"/></fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- CA  -->

          <fo:block-container absolute-position="absolute" top="100mm" left="19mm" width="104mm">
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="32mm" />
					<fo:table-column column-width="8mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="2mm" />
					<fo:table-column column-width="6mm" />
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
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-size="10pt" font-weight="bold" background-color="white"><xsl:value-of select="$label.armorclass"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="armorclass"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" font-family="Carrois">= 10 +</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@armor"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@shield"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="abilities/ability[@key='DEX']/@bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@size"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@natural"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@deflection"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" padding-top="1mm" font-family="Carrois"><fo:block>+</fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="#d4c39b"><xsl:value-of select="armorclass/@misc"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" background-color="white"><xsl:value-of select="$label.ACtouch"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="armorclass/@touch"/></fo:block></fo:table-cell>
							<fo:table-cell number-columns-spanned="14"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" font-size="8pt">
							<fo:table-cell padding="1mm"><fo:block padding-left="2mm" padding-top="1mm" border-style="solid" border-width="thin" font-weight="bold" background-color="white"><xsl:value-of select="$label.ACflatfooted"/></fo:block></fo:table-cell>
							<fo:table-cell padding="1mm" text-align="center"><fo:block padding-top="1mm" border-style="solid" border-width="thin" font-family="Carrois" background-color="white"><xsl:value-of select="armorclass/@flatfooted"/></fo:block></fo:table-cell>
							<fo:table-cell number-columns-spanned="14"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:block-container>

          <!-- Armes et Protections -->

      <fo:block-container absolute-position="absolute" top="130mm" left="19mm" >
	 	<xsl:for-each select="weapons/weapon">	
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm" space-before="5mm" width="64.5">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="21.5mm" />
					<fo:table-column column-width="21.5mm" />
					<fo:table-column column-width="21.5mm" />
					<fo:table-body>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" border-style="solid" border-width="thin">
							<fo:table-cell number-columns-spanned="2" padding-left="5mm" background-color="white"><fo:block><xsl:value-of select="$label.weapon"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" background-color="white"><fo:block><xsl:value-of select="$label.type"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" number-columns-spanned="2" padding-top="1mm" padding-left="2mm"><fo:block><xsl:value-of select="name"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="type"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" border-style="solid" border-width="thin">
							<fo:table-cell text-align="center" background-color="white"><fo:block><xsl:value-of select="$label.attack"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" background-color="white"><fo:block><xsl:value-of select="$label.damage"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" background-color="white"><fo:block><xsl:value-of select="$label.critical"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="4mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="attackBonus"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="damage"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="critical"/></fo:block></fo:table-cell>
						</fo:table-row>
						<xsl:if test="range != ''">
							<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" border-style="solid" border-width="thin">
								<fo:table-cell text-align="center" background-color="white"><fo:block><xsl:value-of select="$label.range"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" number-columns-spanned="2" background-color="white"><fo:block><xsl:value-of select="$label.ammo"/></fo:block></fo:table-cell>
							</fo:table-row>
							<fo:table-row height="4mm" font-size="7pt" display-align="center">
								<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="range"/></fo:block></fo:table-cell>
								<fo:table-cell number-columns-spanned="2" border-style="solid" border-width="thin" text-align="center" padding-top="1mm"><fo:block><xsl:value-of select="ammo"/></fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:if>
					</fo:table-body>
				</fo:table>
			</fo:block>
	 	   </xsl:for-each>	
	 	   
            <fo:block font-size="10pt" padding-left="1mm" padding-top="1mm" space-before="10mm" width="64.5mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="25mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="9.5mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-column column-width="10mm" />
					<fo:table-header>
						<fo:table-row height="2mm" font-size="6pt" display-align="center" font-family="Carrois" border-style="solid" border-width="thin" background-color="white">
							<fo:table-cell padding-left="5mm"><fo:block><xsl:value-of select="$label.armor"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.bonus"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.maxDex"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.penalty"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center"><fo:block><xsl:value-of select="$label.spellfailure"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
				 	<xsl:for-each select="armors/armor">	
						<fo:table-row height="5mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm" padding-left="2mm"><fo:block><xsl:value-of select="name"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="acBonus"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:if test="maxDex != '+100'"><xsl:value-of select="maxDex"/></xsl:if></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="penalty"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="spellFailure/@amount"/>%</fo:block></fo:table-cell>
						</fo:table-row>
					  </xsl:for-each>
						<fo:table-row height="5mm" font-size="7pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
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
          <fo:block-container absolute-position="absolute"
            top="0mm" left="0mm" width="21cm" height="29.7cm"
            background-image="xsl/pathfinder_v1/images/pathfinder_v1_fond.jpg">
            <fo:block/>
          </fo:block-container>

          <!-- Equipement -->
          <fo:block-container absolute-position="absolute" top="15mm" left="19mm" width="94mm">
          
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="49mm" />
					<fo:table-column column-width="12mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" border-style="solid" border-width="thin" background-color="white">
							<fo:table-cell padding-left="2mm" font-size="10pt" padding-top="1mm"><fo:block><xsl:value-of select="$label.gear"/></fo:block></fo:table-cell>
							<fo:table-cell text-align="center" font-size="6pt" font-family="Carrois"><fo:block><xsl:value-of select="$label.weight"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
				 	<xsl:for-each select="gear/item">	
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="."/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="@amount div 10"/></fo:block></fo:table-cell>
						</fo:table-row>
					  </xsl:for-each>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell border-style="solid" border-width="thin" padding-top="1mm"><fo:block></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-right="2mm" border-style="solid" border-width="thin" text-align="right" padding-top="1mm"><fo:block><xsl:value-of select="$label.totalweight"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="sum(//item/@amount) div 10"/> </fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="5mm" display-align="center" border-style="solid" border-width="thin" background-color="white">
							<fo:table-cell number-columns-spanned="2" padding-top="1mm" padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.load"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadLight"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="load/@maxLight"/> </fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadMedium"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="load/@maxMedium"/></fo:block></fo:table-cell>
						</fo:table-row>
						<fo:table-row height="3mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-left="2mm" border-style="solid" border-width="thin" text-align="left" padding-top="1mm"><fo:block><xsl:value-of select="$label.loadHeavy"/></fo:block></fo:table-cell>
							<fo:table-cell border-style="solid" border-width="thin" text-align="center" padding-top="1mm" font-family="Carrois"><fo:block><xsl:value-of select="load/@maxHeavy"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
          
          <!-- Dons, traits et capacités -->
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm" space-before="10mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="4mm" />
					<fo:table-column column-width="90mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" border-style="solid" border-width="thin" background-color="white">
							<fo:table-cell number-columns-spanned="2" padding-top="1mm" padding-left="2mm" font-size="10pt"><fo:block><xsl:value-of select="$label.traits"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
					 	<xsl:for-each select="racetraits/racetrait">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="$short.racetrait"/></fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="@display"/><xsl:if test="text()">&#x00A0;(<xsl:value-of select="."/>)</xsl:if></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
							<fo:table-row height="0.5mm" >
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
							</fo:table-row>
					 	<xsl:for-each select="feats/feat">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="$short.feat"/></fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="@display"/><xsl:if test="text()">&#x00A0;(<xsl:value-of select="."/>)</xsl:if></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
							<fo:table-row height="0.5mm" >
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
							</fo:table-row>
					 	<xsl:for-each select="traits/trait">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="$short.trait"/></fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="@display"/><xsl:if test="text()">&#x00A0;(<xsl:value-of select="."/>)</xsl:if></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
							<fo:table-row height="0.5mm" >
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
								<fo:table-cell border-style="solid" border-width="thin"><fo:block></fo:block></fo:table-cell>
							</fo:table-row>
					 	<xsl:for-each select="specialAbilities/specialAbility">	
							<fo:table-row height="4mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="$short.specialAbility"/></fo:block></fo:table-cell>
								<fo:table-cell padding-left="2mm" padding-right="2mm" border-style="solid" border-width="thin" padding-top="1mm"><fo:block><xsl:value-of select="@display"/><xsl:if test="text()">&#x00A0;(<xsl:value-of select="."/>)</xsl:if></fo:block></fo:table-cell>
							</fo:table-row>
	   				    </xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
		  </fo:block-container>

               <!-- Richesses -->
          
          <fo:block-container absolute-position="absolute" top="15mm" left="90mm" width="101mm">
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="100%" text-align="left">
					<fo:table-column column-width="6mm" />
					<fo:table-column column-width="15mm" />
					<fo:table-column column-width="80mm" />
					<fo:table-header>
						<fo:table-row height="5mm" display-align="center" border-style="solid" border-width="thin" background-color="white">
							<fo:table-cell number-columns-spanned="3" padding-top="1mm" padding-left="3mm" font-size="10pt"><fo:block><xsl:value-of select="$label.treasure"/></fo:block></fo:table-cell>
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

          <!-- Bloc de lanceur de sorts -->

		<xsl:if test="spellcastings/spellcasting">
          <fo:block-container absolute-position="absolute" top="50mm" left="125mm" width="66mm">
            <xsl:for-each select="spellcastings/spellcasting">
            <xsl:variable name="currentClass" select="@casterClass"/>
            <xsl:variable name="casterAbility" select="@abilityKey"/>
            <fo:block font-size="12pt" border-style="solid" border-width="thin" background-color="white" text-align="center" padding-top="1mm" padding-left="2mm"><xsl:value-of select="$label.spells"/>: <xsl:value-of select="@casterClass"/></fo:block>
            <xsl:if test="subclasses/subclass">
	            <fo:block text-align="center" padding-top="1mm" padding-left="2mm" font-size="10pt"><xsl:value-of select="@subclassName"/></fo:block>
	            <fo:block text-align="left" padding-top="1mm" padding-left="2mm" font-size="10pt">
									<xsl:for-each select="subclasses/subclass"><xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="."/></xsl:for-each>
				</fo:block>
			</xsl:if>
            <fo:block font-size="12pt" padding-left="1mm" padding-top="1mm">
				<fo:table table-layout="fixed" width="66mm" text-align="left" border-bottom-style="solid" border-top-style="solid">
					<fo:table-column column-width="18mm" />
					<fo:table-column column-width="16mm" />
					<fo:table-column column-width="16mm" />
					<fo:table-column column-width="16mm" />
					<fo:table-header>
						<fo:table-row height="5mm" font-size="8pt" display-align="center">
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.level"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.spellsPerDay"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.spellDR"/></fo:block></fo:table-cell>
							<fo:table-cell padding-top="2mm" text-align="center" ><fo:block><xsl:value-of select="$label.knownSpells"/></fo:block></fo:table-cell>
						</fo:table-row>
					</fo:table-header>
					<fo:table-body>
						<xsl:for-each select="spellslots/level">
							<xsl:variable name="currentlevel" select="@key"/>
							<fo:table-row height="5mm" font-size="8pt" display-align="center">
								<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block><xsl:value-of select="@key"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin"><xsl:if test="text() >= 0"><xsl:value-of select="."/></xsl:if></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin"><xsl:value-of select="10 + $currentlevel + //abilities/ability[@key=$casterAbility]/@bonus"/></fo:block></fo:table-cell>
								<fo:table-cell text-align="center" font-family="Carrois" padding-top="1mm" padding-bottom="1mm" padding-left="2mm" padding-right="2mm"><fo:block border-style="solid" border-width="thin"><xsl:value-of select="//spellcasting[@casterClass=$currentClass]/knownspellscount/level[@key=$currentlevel]"/></fo:block></fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
				<xsl:if test="knownspells/knownspell">
					<fo:table table-layout="fixed" width="66mm" text-align="left">
						<fo:table-column column-width="10mm" />
						<fo:table-column column-width="56mm" />
						<fo:table-body>
							<xsl:for-each select="spellslots/level">							
								<xsl:variable name="currentlevel" select="@key"/>
								<fo:table-row height="3mm" font-size="6pt" display-align="center">
									<fo:table-cell text-align="center" font-family="Carrois"><fo:block><xsl:value-of select="@key"/> </fo:block></fo:table-cell>
									<fo:table-cell font-family="Glyphicons" padding-top="2mm"><fo:block>
										<xsl:if test="text() >= 1">
										    <xsl:call-template name="repeat">
										      <xsl:with-param name="output" select="'&#xE157;'" />
										      <xsl:with-param name="count" select="." />
										    </xsl:call-template>
									    </xsl:if>
									</fo:block></fo:table-cell>
								</fo:table-row>
								<fo:table-row height="3mm" font-size="6pt" display-align="center">
									<fo:table-cell number-columns-spanned="2" font-family="Carrois"><fo:block>
										<xsl:for-each select="//spellcasting[@casterClass=$currentClass]/knownspells/knownspell[@amount = $currentlevel]">
											<xsl:sort select="text()"/>
											<xsl:if test="position() > 1">, </xsl:if> <xsl:value-of select="."/>
										</xsl:for-each>
									</fo:block></fo:table-cell>
								</fo:table-row>
							</xsl:for-each>
						</fo:table-body>
					</fo:table>
				</xsl:if>
			</fo:block>
			</xsl:for-each>
		</fo:block-container>
	</xsl:if>
         </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
  
	<xsl:template name="repeat">
	  <xsl:param name="output" />
	  <xsl:param name="count" />
	  <xsl:value-of select="$output" />
	  <xsl:if test="$count &gt; 1">
	    <xsl:call-template name="repeat">
	      <xsl:with-param name="output" select="$output" />
	      <xsl:with-param name="count" select="$count - 1" />
	    </xsl:call-template>
	  </xsl:if>
	</xsl:template>

  
</xsl:stylesheet>
