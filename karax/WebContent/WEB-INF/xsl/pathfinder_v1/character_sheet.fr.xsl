<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:import href="character_sheet.xsl"/>
  <xsl:output method="html" encoding="UTF-8"/>
  
  <!-- Isolate locale-specific content -->

  <xsl:variable name="label.total" select="'Total'"/>
  <xsl:variable name="label.misc" select="'Divers'"/>
  <xsl:variable name="label.name" select="'Nom du personnage'"/>
  <xsl:variable name="label.player" select="'Joueur'"/>
  <xsl:variable name="label.level" select="'Niveau'"/>
  <xsl:variable name="label.bonus" select="'Bonus'"/>
  <xsl:variable name="label.penalty" select="'Pénalité'"/>
  <xsl:variable name="label.notes" select="'notes'"/>
  <xsl:variable name="label.age" select="'Âge'"/>
  <xsl:variable name="label.alignment" select="'Alignement'"/>
  <xsl:variable name="label.armorclass" select="'Classe d´Armure'"/>
  <xsl:variable name="label.armorpenalty" select="'Encombrement'"/>
  <xsl:variable name="label.deity" select="'Religion'"/>
  <xsl:variable name="label.domains" select="'Domaines'"/>
  <xsl:variable name="label.eyes" select="'Yeux'"/>
  <xsl:variable name="label.favoredclasses" select="'Prédilection'"/>
  <xsl:variable name="label.speed" select="'Dèplacement'"/>
  <xsl:variable name="label.feats" select="'Dons'"/>
  <xsl:variable name="label.gear" select="'Équipement'"/>
  <xsl:variable name="label.sex" select="'Sexe'"/>
  <xsl:variable name="label.hair" select="'Cheveux'"/>
  <xsl:variable name="label.height" select="'Taille'"/>
  <xsl:variable name="label.hitpoints" select="'PV'"/>
  <xsl:variable name="label.homeland" select="'Origine'"/>
  <xsl:variable name="label.initiative" select="'Initiative'"/>
  <xsl:variable name="label.levels" select="'Classe et Niveau'"/>
  <xsl:variable name="label.race" select="'Race'"/>
  <xsl:variable name="label.racetraits" select="'Traits de race'"/>
  <xsl:variable name="label.savingthrows" select="'Jets de Sauvegarde'"/>
  <xsl:variable name="label.saveFOR" select="'Vigueur'"/>
  <xsl:variable name="label.saveWIL" select="'Volontè'"/>
  <xsl:variable name="label.saveREF" select="'Rèflexes'"/>
  <xsl:variable name="label.saveBase" select="'Base'"/>
  <xsl:variable name="label.saveAbility" select="'Carac'"/>
  <xsl:variable name="label.saveMagic" select="'Magie'"/>
  <xsl:variable name="label.size" select="'Gabarit'"/>
  <xsl:variable name="label.skills" select="'Compètences'"/>
  <xsl:variable name="label.skillAbility" select="'Carac'"/>
  <xsl:variable name="label.skillRanks" select="'Rangs'"/>
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
  <xsl:variable name="label.ACarmor" select="'Armure'"/>
  <xsl:variable name="label.ACshield" select="'Bouclier'"/>
  <xsl:variable name="label.ACnatural" select="'Naturelle'"/>
  <xsl:variable name="label.ACdeflection" select="'Parade'"/>
  <xsl:variable name="label.ACflatfooted" select="'Dèpourvu'"/>
  <xsl:variable name="label.ACtouch" select="'Contact'"/>
  <xsl:variable name="label.bab" select="'Bonus d´Attaque'"/>
  <xsl:variable name="label.cmb" select="'Bonus de Manœuvre'"/>
  <xsl:variable name="label.cmd" select="'Dèf de Manœuvre'"/>
  <xsl:variable name="label.babBonus" select="'Attaque'"/>
  <xsl:variable name="label.weapon" select="'Arme'"/>
  <xsl:variable name="label.attack" select="'Attaque'"/>
  <xsl:variable name="label.critical" select="'Critique'"/>
  <xsl:variable name="label.type" select="'Type'"/>
  <xsl:variable name="label.range" select="'Portée'"/>
  <xsl:variable name="label.ammo" select="'Munitions'"/>
  <xsl:variable name="label.damage" select="'Dégâts'"/>
  <xsl:variable name="label.armor" select="'Protections'"/>
  <xsl:variable name="label.spellfailure" select="'Échec sorts'"/>
  <xsl:variable name="label.languages" select="'Langues'"/>
  <xsl:variable name="label.totalweight" select="'Poids total'"/>
  <xsl:variable name="label.traits" select="'Dons, traits et capacitès'"/>
  <xsl:variable name="label.load" select="'Charge'"/>
  <xsl:variable name="label.loadLight" select="'Lègére'"/>
  <xsl:variable name="label.loadMedium" select="'Intermédiaire'"/>
  <xsl:variable name="label.loadHeavy" select="'Lourde'"/>
  <xsl:variable name="label.treasure" select="'Richesses'"/>
  <xsl:variable name="label.copper" select="'PC'"/>
  <xsl:variable name="label.silver" select="'PA'"/>
  <xsl:variable name="label.gold" select="'PO'"/>
  <xsl:variable name="label.platinum" select="'PP'"/>
  <xsl:variable name="label.spells" select="'Sorts'"/>
  <xsl:variable name="label.spellDR" select="'DD des Sorts'"/>
  <xsl:variable name="label.spellsPerDay" select="'Sorts / jour'"/>
  <xsl:variable name="label.bonusSpells" select="'Sorts en bonus'"/>
  <xsl:variable name="label.knownSpells" select="'Sorts connus'"/>
</xsl:stylesheet>
