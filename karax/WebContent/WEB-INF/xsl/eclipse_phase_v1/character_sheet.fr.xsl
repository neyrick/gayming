<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:import href="character_sheet.xsl"/>
  <xsl:output method="html" encoding="UTF-8"/>
  
  <!-- Isolate locale-specific content -->
  <xsl:variable name="label.background" select="'Historique'"/>
  <xsl:variable name="label.faction" select="'Faction'"/>
  <xsl:variable name="label.morph" select="'Morphe'"/>
  <xsl:variable name="label.gender" select="'Genre'"/>
  <xsl:variable name="label.actualAge" select="'Âge Effectif'"/>
  <xsl:variable name="label.aptitudes" select="'APTITUDES'"/>
  <xsl:variable name="label.COG" select="'COG'"/>
  <xsl:variable name="label.COO" select="'COO'"/>
  <xsl:variable name="label.INT" select="'INT'"/>
  <xsl:variable name="label.SOM" select="'SOM'"/>
  <xsl:variable name="label.WIL" select="'VOL'"/>
  <xsl:variable name="label.REF" select="'REF'"/>
  <xsl:variable name="label.SAV" select="'AST'"/>
  <xsl:variable name="label.base" select="'Base'"/>
  <xsl:variable name="label.morphBonus" select="'Bonus de Morphe'"/>
  <xsl:variable name="label.total" select="'Total'"/>
  <xsl:variable name="label.STATS" select="'STATS'"/>
  <xsl:variable name="label.MOX" select="'AUD'"/>
  <xsl:variable name="label.INIT" select="'INIT'"/>
  <xsl:variable name="label.SPD" select="'RAP'"/>
  <xsl:variable name="label.DB" select="'BD'"/>
  <xsl:variable name="label.WT" select="'SB'"/>
  <xsl:variable name="label.DUR" select="'END'"/>
  <xsl:variable name="label.DR" select="'SM'"/>
  <xsl:variable name="label.TT" select="'ST'"/>
  <xsl:variable name="label.LUC" select="'LUC'"/>
  <xsl:variable name="label.IR" select="'SA'"/>
  <xsl:variable name="label.aptitudeMax" select="'Limite d´Aptitude'"/>
  <xsl:variable name="label.speedMod" select="'Bonus de Rapidité'"/>
  <xsl:variable name="label.movement" select="'Mouvement'"/>
  <xsl:variable name="label.rez" select="'XP'"/>
  <xsl:variable name="label.totalRez" select="'Total'"/>
  <xsl:variable name="label.damage" select="'Dégâts'"/>
  <xsl:variable name="label.wound" select="'Bless.'"/>
  <xsl:variable name="label.stress" select="'Stress'"/>
  <xsl:variable name="label.trauma" select="'Traumas'"/>
  <xsl:variable name="label.traits" select="'Traits d´Ego'"/>
  <xsl:variable name="label.motivations" select="'Motivations'"/>
  <xsl:variable name="label.activeSkills" select="'Compétences Actives'"/>
  <xsl:variable name="label.knowledgeSkills" select="'Connaissances'"/>
  <xsl:variable name="label.linkedAptitude" select="'Aptitude'"/>
  <xsl:variable name="label.specOtherBonus" select="'Spécialité / Notes'"/>
  <xsl:variable name="label.MUSE" select="'MUSE'"/>
  <xsl:variable name="label." select="''"/>
</xsl:stylesheet>