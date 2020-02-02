(ns sicp.util
  (:require [jdk.lang.management.ManagementFactory :as mf :refer [*get-runtime-mx-bean]]))

; This if won't work as expected because both clauses get evaluated regardless
; of the condition
(defn my-if [condition then else]
  (cond condition then :else else))

; Get uptime of the VM
(defn runtime [] (.getUptime (*get-runtime-mx-bean)))
