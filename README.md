# extractor-pickaxe

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.java.com/)
[![PaperMC](https://img.shields.io/badge/PaperMC-1.18.2%2B-green)](https://papermc.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Discord](https://img.shields.io/discord/1350369915521204276?label=Discord&color=7289DA&logo=discord&logoColor=white)](https://discord.com/invite/3K9yrZQRmS)

**extractor-pickaxe** is a **Minecraft** plugin designed specifically for **PaperMC** servers. It allows you to create and manage special pickaxes called *extractors*, which have limited uses and store custom data using Bukkit's **PersistentDataContainer**.

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