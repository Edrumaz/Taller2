const Joi = require('joi');
const express = require('express');
const app = express();

app.use(express.json());

//Dummy Data
const coins = [
   { id: 1, name: 'Dolar', desc: 'Moneda Estadounidense', year: 1900, available: true, symbol: '$', picture: 'test.jpg'},
   { id: 2, name: 'Euro', desc: 'Moneda Europea', year: 1900, available: true, symbol: '€', picture: 'test.jpg'},
   { id: 3, name: 'Yen', desc: 'Moneda de Japon', year: 1900, available: true, symbol: '¥', picture: 'test.jpg'},
   { id: 4, name: 'Colon', desc: 'Moneda Salvadoreña', year: 1900, available: false, symbol: '₡', picture: 'test.jpg'}
];

// Routes handler
app.get('/api/coins', (req, res) => {
   res.send(coins);
});

app.post('/api/coins', (req, res) => {
   const result = validateCoin(req.body);
   if(result.error){ //400 Bad Request
      res.status(400).send(result.error.details[0].message);
      return;
   }
   const coin = {
      id: coins.length + 1,
      name: req.body.name,
      desc: req.body.desc,
      year: req.body.year,
      available: req.body.available,
      symbol: req.body.symbol,
      picture: req.body.picture
   };
   coins.push(coin);
   res.send(coin);
});

app.put('/api/coins/:id', (req, res) => {
   const coin = coins.find(c => c.id === parseInt(req.params.id));
   if (!coin){ // 404 Not found
      res.status(404).send('Moneda no encontrada.');
      return;
   }

   const result = validateCoin(req.body);
   if(result.error){ // 400 Bad Request
      res.status(400).send(result.error.details[0].message);
      return;
   }

  coin.name = req.body.name;
  coin.desc = req.body.desc;
  coin.year = req.body.year;
  coin.available = req.body.available;
  coin.symbol = req.body.symbol;
  coin.picture = req.body.picture;
  res.send(coin);
});

app.delete('/api/coins/:id', (req, res) => {
   const coin = coins.find(c => c.id === parseInt(req.params.id));
   if (!coin) {
      res.status(404).send('Moneda no encontrada.');
      return;
   }

   const index = coins.indexOf(coin);
   coins.splice(index, 1);

   res.send(coin);
});


app.get('/api/coins/:id', (req, res) => {
   const coin = coins.find(c => c.id === parseInt(req.params.id));
   if (!coin) res.status(404).send('Moneda no encontrada.');
   res.send(coin);
});

function validateCoin(coin){
   const schema = {
      name: Joi.string().min(3).required(),
      desc: Joi.string().min(5).required(),
      year: Joi.int().required(),
      available: Joi.boolean(),
      symbol: Joi.string().min(1).required(),
      picture: Joi.string().required()
   };
   return Joi.validate(coin, schema);
}

const port = process.env.PORT || 3000;
app.listen(3000, () => console.log(`Listening on port ${port}...`));
