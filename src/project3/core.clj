(ns project3.core)
(require '[clojure.set :as set])

; name: Nadya Pena
; course: Programming Languages
; assignment: Project 3

(def ranks (range 1 14))
(def suits [:hearts :spades :clubs :diamonds])
(def deck (into [] (for[s suits r ranks] [s r])))

; fn: unique-rands
; params: integer n
; returns: set containing random numbers from 0 - n
; - Generate unique random numbers starting at 0 (inclusive) up to n (exclusive)
 (defn unique-rands [n]
    (let [a-set (set (take n (repeatedly #(rand-int n))))]
      (concat a-set (set/difference (set (take n (range)))
                                    a-set))))

; fn: shuffle-deck 
; params: vector of cards
; returns: vector of cards in random order
;
; - Generate a list of unique random integers from 0 - 51
; - Loop through the deck-of-cards and for each card, choose its corresponding random index from the randoms list
; 	and put that corresponding element into a new vector called shuffled-deck
; - Once we have looped through all 52 cards, we return the shuffled-deck
(defn shuffle-deck [deck-of-cards]
  (def randoms (unique-rands 52))
  (loop [card-index 0 shuffled-deck[]]
    (if (< card-index 52)
      (recur (inc card-index) (conj shuffled-deck (get deck (nth randoms card-index)))) shuffled-deck)))

; fn: deal
; params: vector of cards in random order
; returns: a vector containing subvectors of incresing sizes ( 1 - 8)
;
; - Loop through the shuffled deck and split the cards into 8 piles
; - Using the subvec feature allows dealing piles of increasing sizes (pile 1 has 1 card, pile 2 has 2 cards, etc.) 
; - At the end, put the piles into a vector
(defn deal 
   [deck-of-cards]
   (loop [pile-number 1 shuffled-deck deck-of-cards hand []] ; starting at 0 gives an empty pile subvector, so starting at 1
     (if (< pile-number 8)
       (recur (inc pile-number) (subvec shuffled-deck pile-number) (conj hand (into [] (take pile-number shuffled-deck))))
       (vector (conj hand shuffled-deck))))) ; put the resulting piles into a vector

; runs the shuffle-deck and deal functions
(defn -main[]
 	(println "\n")
    (println "Shuffled Deck: ")
    (println (shuffle-deck deck))
    (println "\n")
    (println "Dealed Piles: ")
    (println (deal (shuffle-deck deck))))