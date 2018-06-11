# currencyApi
WebApi presenting exchange rates from NBP

Project: Website presenting exchange rates from National Bank of Poland  using bank’s api (JSON format). - prices from table A (mid) and table C (bid and ask) for following currencies: gbp, cad, chf, sek, jpy, aud, dkk, czk, eur, huf, nok. 

Used technique: Spring, JSP, JavaScript (charts), Bootstrap. 

Funcionalities

1. Admin panel

Funcionality allows to download archival and current year rates from NBP api to SQL database. JSON files from NBP api has been deserialized using Jackson Library. 
Additionally, ir is opportunity to delete all rates for choosen currency or for indicated period. 

Action in: RateSaveTableAController and RateSaveTableCController. 


2. Charts.
30 days for each currency from basket. (in future new funcionality will be available – charts for other ranges (for example for 30,90 days, month, quater, half year, year or period choosen by user). 

Action in: ChartsController

3. Tables
Section presents newest table A and table C from NBP. 

Action in: RateShowController and RateShowControllerMid. (method: tableNewest.

4. Calculator
Tool allows to calculate amount from polsih currency to foreign currency and from foreign currency to polish. 

Action in: CalculatorController

5. Searcher
Tool allows to search exchange rates using many criteria. For example users can search exchange rates for choosen period, greater or less than choosen value, max or min value for a period. 

Action in: RateShowController and RateShowControllerMid. 
