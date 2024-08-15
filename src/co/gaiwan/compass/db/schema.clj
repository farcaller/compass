(ns co.gaiwan.compass.db.schema)

;; bigdec | bigint | boolean | bytes | double | float | instant |
;; keyword | long | ref | string | symbol | tuple | uuid | uri

(def schema
  [[:user/uuid :uuid "Unique user identifier" :identity]
   [:user/email :string "User email" :identity]
   [:user/name :string "User name, e.g. 'Arne'"]
   [:user/handle :string "User handle, e.g. 'sunnyplexus'"]
   [:user/title :string "User's job title or any description, e.g. 'CEO of Gaiwan'"]
   [:user/image-path :string "User image path in the compass web server"]

   [:discord/id :string "Unique user id on discord, a 'snowflake', i.e. uint64 encoded as string"]
   [:discord/access-token :string "Discord OAuth2 access-token"]
   [:discord/expires-at :instant "Expiration timestamp for the OAuth2 token"]
   [:discord/refresh-token :string "Discord OAuth2 refresh-token"]
   [:discord/avatar-url :string "Discord Avatar URL"]

   [:user-group/orga :boolean "If this group is orga group or not"]
   [:user-group/user-count :long "Number of people in this group"]
   [:user-group/users :ref "Reference points to the user" :many]

   [:session/code :string "Corresponding Pretalx code, to prevent the import from creating duplicates" :identity]
   [:session/title :string "Title of the talk/workshop/activity"]
   [:session/subtitle :string "Subtitle of the session, for talks/workshops = speaker names"]
   [:session/description :string "Full description or abstract"]
   [:session/type :ref "Type of the session"]
   [:session/organized :ref "User who organizes this session"]
   [:session/time :instant "Time the session starts"]
   [:session/duration :string "Duration of the session in ISO interval notation"]
   [:session/location :ref "Where does the session take place"]
   [:session/image :string "Image URL, either absolute, or relative to compass root"]
   [:session/capacity :long "Number of people that are able to join this session"]
   [:session/signup-count :long "Number of people that are currently signing up this session"]
   [:session/ticket-required? :boolean "If this session requires a ticket"]
   [:session/published? :boolean "If this session is published/visible?"]
   [:session/participants :ref "reference points to the user" :many]

   [:session.type/name :string "Type of session, e.g. talk, activity"]
   [:session.type/color :string "CSS color or var reference used for rendering"]

   [:location/name :string "Name of the location" :identity]
   [:oauth/state-id :uuid "State parameter passed along with the oauth flow" :identity]
   [:oauth/redirect-url :string "Location to redirect to after login"]

   [:tito.registration/id :long "Ti.to registration (order) unique id" :identity]
   [:tito.registration/reference :string "Ti.to registration reference code, 4 characters alphanumeric" :identity]
   [:tito.registration/email :string "Email address associated with the ti.to order"]
   [:tito.registration/name :string "Name on the ti.to order"]
   [:tito.registration/state :string "State the order is in, one of complete, incomplete"]

   [:tito.ticket/id :long "Ti.to ticket unique id" :identity]
   [:tito.ticket/reference :string "Ti.to ticket referecen code, same as registration ref code + sequential number, e.g. A4FB-2" :identity]
   [:tito.ticket/name :string "Name assigned to the ticket"]
   [:tito.ticket/email :string "Email assigned to the ticket"]
   [:tito.ticket/registration :ref "The registration (order) this ticket is part of"]
   [:tito.ticket/release :ref "The release (ticket type) this ticket has"]
   [:tito.ticket/state :string "The state this ticket is in, `new`/`reminder` means it's unassigned, and name/email will be blank/nil."]

   [:tito.release/id :long "Unique id of the registration (ticket type)" :identity]
   [:tito.release/title :string "Human readable name of the ticket type, e.g. `Early Bird`"]
   [:tito.release/slug :string "URL slug for the ticket type"]])

(defn inflate-schema [s]
  (for [[ident type doc & flags] s]
    (cond-> {:db/ident     ident
             :db/valueType (keyword "db.type" (name type))
             :db/doc       doc
             :db/cardinality (if (some #{:many} flags)
                               :db.cardinality/many
                               :db.cardinality/one)}
      (some #{:identity} flags)
      (assoc :db/unique :db.unique/identity)
      (some #{:value} flags)
      (assoc :db/unique :db.unique/value)
      (some #{:component} flags)
      (assoc :db/isComponent true))))

(defn schema-tx []
  (inflate-schema schema))

(comment
  (user/reset))
