const express = require('express');
const path    = require('path');
const fs = require('fs');
const archiver = require('archiver');
const app     = express();

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
  const projectFolder = path.join(__dirname, '../Project_portfolio_Hub_Application');
  const zipPath = path.join(__dirname, 'temp', 'Project_Portfolio_Hub.zip');

  // Ensure temp folder exists
  fs.mkdirSync(path.dirname(zipPath), { recursive: true });

  // Stream zip to file
  const output = fs.createWriteStream(zipPath);
  const archive = archiver('zip', { zlib: { level: 9 } });

  output.on('close', () => {
    // Trigger download after zip is complete
    res.download(zipPath, 'Project_Portfolio_Hub.zip', (err) => {
      if (err) {
        console.error('Download error:', err);
        return res.status(500).send('Download failed');
      }
      // Optional: delete zip after sending
      fs.unlinkSync(zipPath);
    });
  });

  archive.on('error', (err) => {
    console.error('Archive error:', err);
    res.status(500).send('Could not create zip');
  });

  archive.pipe(output);
  archive.directory(projectFolder, false); // `false` = don’t include root dir
  archive.finalize();
});

app.listen(8080, () => {
  console.log('App listening on port 8080!');
});
