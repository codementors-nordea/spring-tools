Poniższy kod zawiera logikę odpowiedzialną za przydzielanie podwyżek. Poniżej znajdują się zasady, które musi spełnić żądanie o podwyżkę, aby zostało zaakceptowane:
1. Aktualna i proponowana pensja nie mogą być null
2. Data podwyżki musi być w przyszłości, ale nie więcej niż trzy miesiące od dzisiaj -> to może wymagać napisania własnego walidatora
3. Nowa pensja nie może być większa niż 30% aktualnej pensji. Procent podwyżki powinien być parametryzowany z poziomu adnotacji -> to może wymagać napisania własnego walidatora
4. Dozwolone są maksymalnie dwie wcześniejsze podwyżki
5. Lista nie może być nullem
6. Id poprzednich podwyżek powinno być w formacie <liczba>.<string>.<liczba>-<liczba>.
7. Podwyżka jest odrzucona jeżeli budżet został przekroczony