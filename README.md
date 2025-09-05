# extractor-pickaxe

<p align="center">
  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/Java-17+-blue" alt="Java"/>
  </a>
  <a href="https://papermc.io/">
    <img src="https://img.shields.io/badge/PaperMC-1.18.2%2B-green" alt="PaperMC"/>
  </a>
  <a href="LICENSE">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License"/>
  </a>
  <a href="https://discord.com/invite/3K9yrZQRmS">
    <img src="https://img.shields.io/discord/1350369915521204276?label=Discord&color=7289DA&logo=discord&logoColor=white" alt="Discord"/>
  </a>
</p>

**extractor-pickaxe** is a **Minecraft** plugin for PaperMC that lets you create powerful pickaxes capable of mining spawner blocks, even in protected **WorldGuard** regions.

> ⚠️ This project is implemented with PaperMC in mind and may not work properly on other server implementations.

<br>
<p align="center">
    <a href="https://discord.com/invite/3K9yrZQRmS">
        <img src="https://imgur.com/DvyC4jL.png" width="600" alt="nothing">
    </a>
    <br/>
    <i>If you need help, join the discord server.</i>
</p>
<br>

---

## Features

- Create custom extractor pickaxes with persistent data.
- Set and decrement the number of uses per extractor.
- Convert regular items into extractors.
- Safe usage system that prevents modifying non-extractor items.
- Fully compatible with PaperMC and Bukkit API.

---

## Commands

| Name                |          Permission          |                             Description                             |
|---------------------|:----------------------------:|:-------------------------------------------------------------------:|
| /extractor get      | extractorpickaxe.command.use |                   Get a netherite mining pickaxe                    |
| /extractor convert  | extractorpickaxe.command.use |            Turn the pick in your hand into an extractor             |
| /extractor set-uses | extractorpickaxe.command.use | Set the number of uses for the extractor pick you have in your hand |

---

## Installation

1. Download the plugin JAR.
2. Place it in the `plugins` folder of your PaperMC server.
3. Restart the server to load the plugin.
4. And it's ready to use.

---

## Downloads

You can download the plugin from the following pages:

- [Modrinth](https://modrinth.com/)

---

## For Developers

If you're a developer and want a quick overview of how the plugin works:

The plugin stores a **type of data (byte)** inside a pickaxe (`ItemStack`) using Bukkit's **PersistentDataContainer**.  
When a player breaks a spawner, the plugin checks if the pickaxe contains that specific data.  
If it does, the plugin handles the action accordingly. Simple and straightforward.