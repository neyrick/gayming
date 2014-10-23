<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:import href="character_sheet.xsl"/>
  <xsl:output method="html" encoding="UTF-8"/>
  
  <!-- Isolate locale-specific content -->

  <xsl:variable name="label.name" select="'Nom du personnage'"/>
  <xsl:variable name="label.player" select="'Joueur'"/>
  <xsl:variable name="label.bonus" select="'Bonus'"/>
  <xsl:variable name="label.age" select="'Âge'"/>
  <xsl:variable name="label.alignment" select="'Alignement'"/>
  <xsl:variable name="label.armorclass" select="'Classe d Armure'"/>
  <xsl:variable name="label.armorpenalty" select="'Encombrement'"/>
  <xsl:variable name="label.deity" select="'Religion'"/>
  <xsl:variable name="label.domains" select="'Domaines'"/>
  <xsl:variable name="label.eyes" select="'Yeux'"/>
  <xsl:variable name="label.favoredclasses" select="'Prédilection'"/>
  <xsl:variable name="label.feats" select="'Dons'"/>
  <xsl:variable name="label.gear" select="'Équipement'"/>
  <xsl:variable name="label.sex" select="'Sexe'"/>
  <xsl:variable name="label.hair" select="'Cheveux'"/>
  <xsl:variable name="label.height" select="'Taille'"/>
  <xsl:variable name="label.hitpoints" select="'PV'"/>
  <xsl:variable name="label.homeland" select="'Origine'"/>
  <xsl:variable name="label.initiative" select="'Initiative'"/>
  <xsl:variable name="label.knownspells" select="'Sorts connus'"/>
  <xsl:variable name="label.levels" select="'Classe et Niveau'"/>
  <xsl:variable name="label.race" select="'Race'"/>
  <xsl:variable name="label.racetraits" select="'Traits de race'"/>
  <xsl:variable name="label.saves" select="'Sauvegarde'"/>
  <xsl:variable name="label.saveFOR" select="'Vigueur'"/>
  <xsl:variable name="label.saveWIL" select="'Volonté'"/>
  <xsl:variable name="label.saveREF" select="'Réflexes'"/>
  <xsl:variable name="label.size" select="'Cat. Taille'"/>
  <xsl:variable name="label.skills" select="'Compétences'"/>
  <xsl:variable name="label.subrace" select="'Sous-race'"/>
  <xsl:variable name="label.weight" select="'Poids'"/>
  <xsl:variable name="label.abilitySTR" select="'FOR'"/>
  <xsl:variable name="label.abilityDEX" select="'DEX'"/>
  <xsl:variable name="label.abilityCON" select="'CON'"/>
  <xsl:variable name="label.abilityINT" select="'INT'"/>
  <xsl:variable name="label.abilityWIS" select="'SAG'"/>
  <xsl:variable name="label.abilityCHA" select="'CHA'"/>
  <xsl:variable name="label.abilityLongSTR" select="'FORCE'"/>
  <xsl:variable name="label.abilityLongDEX" select="'DEXTÉRITÉ'"/>
  <xsl:variable name="label.abilityLongCON" select="'CONSTITUTION'"/>
  <xsl:variable name="label.abilityLongINT" select="'INTELLIGENCE'"/>
  <xsl:variable name="label.abilityLongWIS" select="'SAGESSE'"/>
  <xsl:variable name="label.abilityLongCHA" select="'CHARISME'"/>

</xsl:stylesheet>
