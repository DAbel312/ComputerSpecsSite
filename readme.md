## Computer Specs Site

Website which allows you to compare computer specs like cpus, gpus or ssds. You can compare the technical specs of them and also a calculated performance score. 

## About the Project

This project is a full-stack web application that allows users to compare hardware components based on technical specifications and calculated scores

The main focus of this project is:
 - Designing a scalable backend architecture
 - Modeling structured hardware data in PostgreSQL
 - Implementing scoring logic
 - clean separation of frontend and backend

## Features

 - compare multiple gpus
 - publish and read articles about components
 - detailed technical specifications
 - calculated scores (for example efficiency or performance score)
 - responsive UI

## Architecture

 - Frontend: Vue 3 + TypeScript + SCSS
 - Backend: Spring Boot (REST API)
 - Database: PostgreSQL
 - Communication: JSON over HTTP

## Score calculation

 - there are different scores which are in the database
 - compute score which represents compute performance
 - gaming score which represents gaming performance
 - efficiency for each based on tdpw and performance score

## Planned Features
 - compare cpus and ssds
 - rating system for users
 - refactoring to TailwindCSS
 - implementation of NuxtUI
 - little idle game



