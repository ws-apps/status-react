(ns status-im.ui.screens.group.navigation
  (:require [status-im.ui.screens.navigation :as nav]))

(defmethod nav/preload-data! :add-contacts-toggle-list
  [db _]
  (assoc db :group/selected-contacts #{}))

(defmethod nav/preload-data! :add-participants-toggle-list
  [db _]
  (assoc db :selected-participants #{}))

(defmethod nav/preload-data! :new-public-chat
  [db]
  (dissoc db :public-group-topic))

(defmethod nav/preload-data! :reorder-groups
  [db [_ _]]
  (assoc db :group/groups-order (->> (vals (:group/contact-groups db))
                                     (remove :pending?)
                                     (sort-by :order >)
                                     (map :group-id))))
