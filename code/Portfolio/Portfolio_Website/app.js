const express = require('express');
const path = require('path');
const fs = require('fs');
const archiver = require('archiver');
const app = express();

const PROJECT_Website = path.join(__dirname);

app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended: true }));
app.use('/images', express.static(path.join(__dirname, 'images')));

app.get('/', (req, res) => {
  res.render('page/index', {
    fileName: path.basename(PROJECT_Website)
  });
});

app.get('/projects', (req, res) => {
  res.render('page/projects', {
    fileName: path.basename(PROJECT_Website)
  });
});

app.get('/api/download_project', (req, res) => {
  const exePath = path.join(__dirname, 'AppExeFile', 'Project_Portfolio_Hub.exe');
  res.download(exePath, 'Project_Portfolio_Hub.exe', (err) => {
    if (err) {
      console.error('Download error:', err);
      res.status(500).send('Failed to download project.');
    }
  });
});

app.listen(8080, () => {
  console.log('App listening on port 8080!');
});
